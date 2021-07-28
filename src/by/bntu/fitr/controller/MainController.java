package by.bntu.fitr.controller;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

import by.bntu.fitr.entity.Equation;
import by.bntu.fitr.jdbc.JDBCUtils;
import by.bntu.fitr.model.*;
import by.bntu.fitr.model.charts.AreaChartHandler;
import by.bntu.fitr.model.charts.LineChartHandler;
import by.bntu.fitr.model.main_logic.DifEquation;
import by.bntu.fitr.model.main_logic.Generator;
import by.bntu.fitr.model.main_logic.Parser;
import by.bntu.fitr.model.microsoft_api.ExcelHandler;
import by.bntu.fitr.model.microsoft_api.WordHandler;
import by.bntu.fitr.model.storage.SettingsProperties;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class MainController {
    private LineChartHandler lineChartHandler = new LineChartHandler();
    private AreaChartHandler areaChartHandler = new AreaChartHandler();
    private Navigator navigator = new Navigator();
    private boolean flag = true;
    private double[] x;
    private double[] y;
    private List<String> info;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField equation;

    @FXML
    private TextField lowerLim;

    @FXML
    private TextField upperLim;

    @FXML
    private TextField step;

    @FXML
    private Button calculateBtn;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private NumberAxis xAxis1;

    @FXML
    private NumberAxis yAxis1;

    @FXML
    private AreaChart<?, ?> areaChart;

    @FXML
    private Button menuBtn;

    @FXML
    private NumberAxis xAxis2;

    @FXML
    private NumberAxis yAxis2;

    @FXML
    private TextField yInput;

    @FXML
    private TextArea table;

    @FXML
    private Label label1;

    @FXML
    private MenuItem excel;

    @FXML
    private MenuItem excelOpener;

    @FXML
    private MenuItem wordOpener;

    @FXML
    private MenuItem fillData;

    @FXML
    private MenuItem word;

    @FXML
    void calculateEquationAction(MouseEvent event) throws RemoteException, MalformedURLException, NotBoundException {

        Parser parser = (Parser) Naming.lookup("rmi://localhost:5099/parser");
        parser.setEquation(equation.getText());


        DifEquation difEquation = new DifEquation(Double.parseDouble(lowerLim.getText())
                , Double.parseDouble(upperLim.getText()), Double.parseDouble(step.getText())
                , Double.parseDouble(yInput.getText()), parser);

        try {
            difEquation.rungeMethod();
            info = difEquation.getInfo();

            service3.restart();
            otherOperations(difEquation);

            x = difEquation.getX();
            y = difEquation.getY();
        }

        catch (RuntimeException exception){
            getAlert("Проверьте правильность введенных данных","Ошибка");
        }

    }

    @FXML
    void navigateToMenuBtn(MouseEvent event) {
        navigator.navigate(menuBtn, "../view/menu.fxml");
    }


    @FXML
    void fillExcelFile(ActionEvent event) {
        service1.restart();
    }

    @FXML
    void fillWordAction(ActionEvent event) {
        service2.restart();
    }

    @FXML
    void openExcel(ActionEvent event) {
        try {
            String cmd = "C:\\Program Files (x86)\\Microsoft Office\\Office15\\EXCEL.EXE C:\\Users\\Artyom\\IdeaProjects\\course_work\\src\\by\\bntu\\fitr\\templates\\result.xlsx";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    void openWord(ActionEvent event) {
        try {
            String cmd = "C:\\Program Files (x86)\\Microsoft Office\\Office15\\WINWORD.EXE C:\\Users\\Artyom\\IdeaProjects\\course_work\\src\\by\\bntu\\fitr\\templates\\result.docx";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @FXML
    void fillDataAction(ActionEvent event) {
        Equation eq = Generator.getEquation();

        yInput.setText(String.valueOf(eq.getInitY()));
        upperLim.setText(String.valueOf(eq.getUpperLim()));
        lowerLim.setText(String.valueOf(eq.getLowerLim()));
        step.setText(String.valueOf(eq.getStep()));
        equation.setText(eq.getEquation());
    }

    @FXML
    void checkHelper(ActionEvent event) {
        try {
            Runtime.getRuntime().exec("hh.exe C:\\Users\\Artyom\\IdeaProjects\\course_work\\src\\by\\bntu\\fitr\\templates\\helpFileA.chm");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    Service<Void> service1 = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    ExcelHandler.handle(x, y);
                    return null;
                }
            };
        }
    };

    Service<Void> service2 = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    WordHandler.handle(info);
                    return null;
                }
            };
        }
    };

    Service<Void> service3 = new Service<Void>() {
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    JDBCUtils.loadProperties();
                    try (Connection connection = JDBCUtils.getConnection()) {
                        JDBCUtils.insert(connection, "INSERT INTO equation(eq,lower_lim,upper_lim,step,init_y) VALUES(?,?,?,?,?)"
                                , equation.getText(), Double.parseDouble(lowerLim.getText()), Double.parseDouble(upperLim.getText())
                                , Double.parseDouble(step.getText()), Double.parseDouble(yInput.getText()));
                    }
                    return null;
                }
            };
        }
    };

    @FXML
    void initialize() {
        CheckSettings.checkTypeOfChartSettings(lineChart, areaChart);
        CheckSettings.checkShowTableSettings(table, label1);
        CheckSettings.checkAnimationSettings(lineChart, areaChart);
        CheckSettings.checkDotSettings(lineChart, areaChart);
    }


    public void otherOperations(DifEquation difEquation) {

        if (SettingsProperties.typeOfChart == 1) {

            if (!SettingsProperties.isChartStackedTypeEnabled) {
                lineChart.getData().clear();
            }

            lineChartHandler.createChart(lineChart, difEquation.getX(), difEquation.getY());
        } else {

            if (!SettingsProperties.isChartStackedTypeEnabled) {
                areaChart.getData().clear();
            }

            areaChartHandler.createChart(areaChart, difEquation.getX(), difEquation.getY());
        }


        if (SettingsProperties.isTableShowEnabled) {
            table.setText(fillTable(difEquation.getX(), difEquation.getY()));
        }

    }

    public String fillTable(double[] x, double[] y) {
        StringBuilder queryStr = new StringBuilder();
        for (int i = 0; i < x.length; i++) {
            queryStr.append("x: = " + String.format("%.3f", x[i]) + "\t\t");
            queryStr.append("y: = " + String.format("%.3f", y[i]) + "\n");
        }
        return queryStr.toString();
    }

    public void getAlert(String content, String header) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.setHeaderText(header);
        alert.showAndWait();
    }
}
