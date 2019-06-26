package controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import org.hibernate.SessionFactory;

import java.util.Random;


public class KlausurStatistikController {
    private SessionFactory sessionFactory;
    private Klausur klausur;
    @FXML
    private GridPane klausurStatistikGridPane;


    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    void setupController(Klausur klausur) {
        this.klausur = klausur;
        createHistogram(klausur);
    }

    public void createHistogram(Klausur klausur) {
        prepareData();
        groupData();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> barChart = new BarChart<>(xAxis,yAxis);
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);

        xAxis.setLabel("Range");
        yAxis.setLabel("Relative Häufigkeit");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Histogram");
        series1.getData().add(new XYChart.Data("1,0", group[0]));
        series1.getData().add(new XYChart.Data("1,3", group[1]));
        series1.getData().add(new XYChart.Data("1,7", group[2]));
        series1.getData().add(new XYChart.Data("2,0", group[3]));
        series1.getData().add(new XYChart.Data("2,3", group[4]));
        series1.getData().add(new XYChart.Data("2,7", group[5]));
        series1.getData().add(new XYChart.Data("3,0", group[6]));
        series1.getData().add(new XYChart.Data("3,3", group[7]));
        series1.getData().add(new XYChart.Data("3,7", group[8]));
        series1.getData().add(new XYChart.Data("4,0", group[9]));
        series1.getData().add(new XYChart.Data("5,0", group[10]));

        barChart.getData().addAll(series1);

        klausurStatistikGridPane.add(barChart, 0,0);
    }

    int DATA_SIZE = 1000;
    int data[] = new int[DATA_SIZE];
    int group[] = new int[11];

    private void prepareData(){

        Random random = new Random();
        for(int i=0; i<DATA_SIZE; i++){
            data[i] = random.nextInt(100);
        }
    }

    private void groupData(){
        for(int i=0; i<11; i++){
            group[i]=0;
        }
        for(int i=0; i<DATA_SIZE; i++){
            if(data[i]<=10){
                group[0]++;
            }else if(data[i]<=20){
                group[1]++;
            }else if(data[i]<=30){
                group[2]++;
            }else if(data[i]<=40){
                group[3]++;
            }else if(data[i]<=50){
                group[4]++;
            }else if(data[i]<=60){
                group[5]++;
            }else if(data[i]<=70){
                group[6]++;
            }else if(data[i]<=80){
                group[7]++;
            }else if(data[i]<=90){
                group[8]++;
            }else if(data[i]<=100){
                group[9]++;
            } else if(data[i]<=110){
                group[10]++;
            }
        }
    }
}
