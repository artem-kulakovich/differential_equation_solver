package by.bntu.fitr.controller;
import java.net.URL;
import java.util.ResourceBundle;
import by.bntu.fitr.model.Navigator;
import by.bntu.fitr.model.storage.SettingsProperties;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;


public class SettingsController {
    private Navigator navigator = new Navigator();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkAnimation;

    @FXML
    private CheckBox checkDot;

    @FXML
    private CheckBox checkStackedType;

    @FXML
    private CheckBox checkTable;

    @FXML
    private Button menuBtn;

    @FXML
    private RadioButton areaChartRb;

    @FXML
    private RadioButton lineChartRb;


    @FXML
    void navigateToMenuAction(MouseEvent event) {
        navigator.navigate(menuBtn, "../view/menu.fxml");
    }

    @FXML
    void setAnimationAction(MouseEvent event) {
        SettingsProperties.isAnimationEnabled = setProperties(checkAnimation);
    }

    @FXML
    void setDotAction(MouseEvent event) {
        SettingsProperties.isDotEnabled = setProperties(checkDot);
    }

    @FXML
    void setShowTableTypeAction(MouseEvent event) {
        SettingsProperties.isTableShowEnabled = setProperties(checkTable);
    }

    @FXML
    void setStackedTypeAction(MouseEvent event) {
        SettingsProperties.isChartStackedTypeEnabled = setProperties(checkStackedType);
    }


    @FXML
    void setLineChartAction(MouseEvent event) {
        SettingsProperties.typeOfChart = 1;
    }


    @FXML
    void setAreaChartAction(MouseEvent event) {
        SettingsProperties.typeOfChart = 2;
    }

    @FXML
    void initialize() {

        ToggleGroup toggleGroup = new ToggleGroup();
        areaChartRb.setToggleGroup(toggleGroup);
        lineChartRb.setToggleGroup(toggleGroup);
        setTypeOfChart(SettingsProperties.typeOfChart, lineChartRb, areaChartRb);


        switcher(SettingsProperties.isAnimationEnabled, checkAnimation);
        switcher(SettingsProperties.isChartStackedTypeEnabled, checkStackedType);
        switcher(SettingsProperties.isDotEnabled, checkDot);
        switcher(SettingsProperties.isTableShowEnabled, checkTable);
    }


    public void switcher(boolean properties, CheckBox checkBox) {
        if (properties) {
            checkBox.setSelected(true);
        }

    }


    public boolean setProperties(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    public void setTypeOfChart(int id, RadioButton... radioButtons) {
        if (id == 1) {
            radioButtons[0].setSelected(true);
        } else if (id == 2) {
            radioButtons[1].setSelected(true);
        }
    }
}
