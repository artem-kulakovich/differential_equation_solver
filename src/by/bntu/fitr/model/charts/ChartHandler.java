package by.bntu.fitr.model.charts;

import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public abstract class ChartHandler{
    public abstract void createChart(Object chart, double[] x, double[] y);
}
