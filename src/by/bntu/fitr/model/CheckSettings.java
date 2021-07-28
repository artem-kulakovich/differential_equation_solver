package by.bntu.fitr.model;

import by.bntu.fitr.model.storage.SettingsProperties;

import javafx.scene.chart.AreaChart;


import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;


public class CheckSettings {


    public static void checkAnimationSettings(LineChart<?, ?> lineChart, AreaChart<?, ?> areaChart) {
        lineChart.setAnimated(SettingsProperties.isAnimationEnabled);
        areaChart.setAnimated(SettingsProperties.isAnimationEnabled);
    }

    public static void checkTypeOfChartSettings(LineChart<?, ?> lineChart, AreaChart<?, ?> areaChart) {
        System.out.println(SettingsProperties.typeOfChart);
        if (SettingsProperties.typeOfChart == 1) {
            lineChart.setVisible(true);
            areaChart.setVisible(false);
        } else {
            areaChart.setVisible(true);
            lineChart.setVisible(false);
        }
    }

    public static void checkDotSettings(LineChart<?, ?> lineChart, AreaChart<?, ?> areaChart) {
        areaChart.setCreateSymbols(SettingsProperties.isDotEnabled);
        lineChart.setCreateSymbols(SettingsProperties.isDotEnabled);
    }

    public static void checkShowTableSettings(TextArea table, Label label) {
        if (SettingsProperties.isTableShowEnabled) {
            table.setVisible(true);
            label.setVisible(true);
        } else {
            table.setVisible(false);
            label.setVisible(false);
        }
    }
}



