package by.bntu.fitr.model.charts;


import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;

public class AreaChartHandler extends ChartHandler{
    @Override
    public void createChart(Object chart, double[] x, double[] y) {
        XYChart.Series series = new XYChart.Series();
        for(int i = 0;i<x.length;i++){
            series.getData().addAll(new XYChart.Data(x[i],y[i]));
        }
        series.setName("График функции");
        ((AreaChart<?,?>)(chart)).getData().addAll(series);
    }
}
