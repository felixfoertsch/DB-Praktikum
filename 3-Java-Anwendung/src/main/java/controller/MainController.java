package controller;

import dataimport.ImporterImpl;
import dataimport.model.Universitaet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.klausur.Klausur;
import org.controlsfx.control.MasterDetailPane;
import org.hibernate.Session;
import services.HibernateService;


public class MainController {

    private HibernateService hibernateService;

    @FXML
    private BorderPane mainBorderPane;

    public MainController() {

    }

    public void setupController(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
    }

    @FXML
    private void viewKlausuren() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Klausuren.fxml"));
        MasterDetailPane pane = loader.load();

        KlausurenController c = loader.getController();
        c.setupController(hibernateService);

        mainBorderPane.setCenter(pane);
    }

    @FXML
    public void importButtonClicked(Event e) {
        var importer = new ImporterImpl();
        var universitaet = new Universitaet();
        try {
            var files = importer.importCSVtoMemory();
            universitaet = importer.parseCSVandCreateModel(files);
            importer.checkMultiplicities(universitaet);
            importer.persistModel(universitaet);
            viewKlausuren();
        } catch (Exception npe) {
            System.out.println("Import canceled.");
        }
    }

    @FXML
    public void aboutButtonClicked(Event e) {
        var vBox = new VBox(new Label("Version: Aufgabe 3"));
        vBox.setPrefHeight(50);
        vBox.setPrefWidth(200);
        var scene = new Scene(vBox);
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
