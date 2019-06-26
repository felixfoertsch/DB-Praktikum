package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import model.relationen.KlausurTeilnahme;


public class KlausurStatistikController {
    private int[] notenverteilung = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    @FXML
    private GridPane klausurStatistikGridPane;

    void setupController(Klausur klausur) {
        createHistogram(klausur);
    }

    private void createHistogram(Klausur klausur) {
        prepareData(klausur);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);

        xAxis.setLabel("Range");
        yAxis.setLabel("Relative Häufigkeit");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Notenverteilung");
        series1.getData().add(new XYChart.Data("1,0", notenverteilung[0]));
        series1.getData().add(new XYChart.Data("1,3", notenverteilung[1]));
        series1.getData().add(new XYChart.Data("1,7", notenverteilung[2]));
        series1.getData().add(new XYChart.Data("2,0", notenverteilung[3]));
        series1.getData().add(new XYChart.Data("2,3", notenverteilung[4]));
        series1.getData().add(new XYChart.Data("2,7", notenverteilung[5]));
        series1.getData().add(new XYChart.Data("3,0", notenverteilung[6]));
        series1.getData().add(new XYChart.Data("3,3", notenverteilung[7]));
        series1.getData().add(new XYChart.Data("3,7", notenverteilung[8]));
        series1.getData().add(new XYChart.Data("4,0", notenverteilung[9]));
        series1.getData().add(new XYChart.Data("5,0", notenverteilung[10]));

        barChart.getData().addAll(series1);

        klausurStatistikGridPane.add(barChart, 0, 0);
    }


    private void prepareData(Klausur klausur) {

        for (KlausurTeilnahme kt : klausur.getKlausurTeilnahmen()) {
            switch (kt.getNote().toString()) {
                case "1.0":
                    notenverteilung[0]++;
                    break;
                case "1.3":
                    notenverteilung[1]++;
                    break;
                case "1.7":
                    notenverteilung[2]++;
                    break;
                case "2.0":
                    notenverteilung[3]++;
                    break;
                case "2.3":
                    notenverteilung[4]++;
                    break;
                case "2.7":
                    notenverteilung[5]++;
                    break;
                case "3.0":
                    notenverteilung[6]++;
                    break;
                case "3.3":
                    notenverteilung[7]++;
                    break;
                case "3.7":
                    notenverteilung[8]++;
                    break;
                case "4.0":
                    notenverteilung[9]++;
                    break;
                case "5.0":
                    notenverteilung[10]++;
                    break;
                default:
                    break;
            }
        }
    }

}
