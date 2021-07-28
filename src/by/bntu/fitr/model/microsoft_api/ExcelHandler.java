package by.bntu.fitr.model.microsoft_api;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;


import java.io.FileOutputStream;
import java.io.IOException;


public class ExcelHandler {
    public static void handle(double[] xCords, double[] yCords) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("myList");

        Row row = sheet.createRow(0);
        for (int i = 0; i < xCords.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(xCords[i]);
        }

        row = sheet.createRow(1);
        for (int i = 0; i < yCords.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(yCords[i]);
        }

        createChart(sheet, getDoubleArray(xCords) ,getDoubleArray(yCords));
        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Artyom\\IdeaProjects\\course_work\\src\\by\\bntu\\fitr\\templates\\result.xlsx");
        workbook.write(fileOutputStream);


        fileOutputStream.close();
        workbook.close();
    }

    public static void createChart(Sheet sheet, Double[] xCords, Double[] yCords) {
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 10, 20, 30);
        XSSFChart chart = drawing.createChart(anchor);

        chart.setTitleText("Test results");
        chart.setTitleOverlay(false);

        XDDFDataSource<Number> xValues = XDDFDataSourcesFactory.fromArray(xCords);
        XDDFNumericalDataSource<Number> yValues = XDDFDataSourcesFactory.fromArray(yCords);


        XDDFCategoryAxis xddfChartAxis1 = chart.createCategoryAxis(AxisPosition.BOTTOM);
        XDDFValueAxis xddfChartAxis2 = chart.createValueAxis(AxisPosition.LEFT);

        XDDFLineChartData data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, xddfChartAxis1, xddfChartAxis2);

        XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(xValues, yValues);
        series.setTitle("Area", null);
        series.setSmooth(false);
        series.setMarkerStyle(MarkerStyle.STAR);

        chart.plot(data);
    }

    private static Double[] getDoubleArray(double arr[]) {
        Double[] cords = new Double[arr.length];

        for (int i = 0; i < arr.length; i++) {
            cords[i] = arr[i];
        }
        return cords;
    }
}
