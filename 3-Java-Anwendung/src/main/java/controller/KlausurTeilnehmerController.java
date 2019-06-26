package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import model.relationen.KlausurTeilnahme;


public class KlausurTeilnehmerController {

    // Tab: Teilnehmer
    @FXML
    TableView<KlausurTeilnahme> klausurTeilnehmerTableView;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerId;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerName;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerMatrNr;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerPunkte;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerNote;

    @FXML
    private GridPane klausurStatistikGridPane;

    void setupController(Klausur klausur) {
        klausurTeilnehmerTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("BUH");
            }
        });

        klausurTeilnehmerId.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getId().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerName.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getVorname() + " " + l.getValue().getStudent().getNachname());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerMatrNr.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getMatrikelNr());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerPunkte.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getPunkte().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerNote.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getNote().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        ObservableList<KlausurTeilnahme> kt = FXCollections.observableArrayList(klausur.getKlausurTeilnahmen());
        klausurTeilnehmerTableView.setItems(kt);
        klausurTeilnehmerTableView.getSortOrder().add(klausurTeilnehmerName);
    }

}
