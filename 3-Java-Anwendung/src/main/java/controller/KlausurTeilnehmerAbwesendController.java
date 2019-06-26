package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import model.relationen.KlausurTeilnahme;

import java.util.ArrayList;
import java.util.List;


public class KlausurTeilnehmerAbwesendController {
    @FXML
    TableView<KlausurTeilnahme> klausurTeilnehmerAbwesendTableView;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerAbwesendId;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerAbwesendName;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerAbwesendMatrNr;
    @FXML
    TableColumn<KlausurTeilnahme, String> klausurTeilnehmerAbwesendEntschuldigt;

    void setupController(Klausur klausur) {
        List<KlausurTeilnahme> abwensendeKlausurTeilnehmer = new ArrayList<>();

        for (KlausurTeilnahme kt : klausur.getKlausurTeilnahmen()) {
            if (!kt.getErschienen()) {
                abwensendeKlausurTeilnehmer.add(kt);
            }
        }

        klausurTeilnehmerAbwesendId.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getId().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerAbwesendName.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getVorname() + " " + l.getValue().getStudent().getNachname());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerAbwesendMatrNr.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getMatrikelNr());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        klausurTeilnehmerAbwesendEntschuldigt.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getEntschuldigt().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        ObservableList<KlausurTeilnahme> kt = FXCollections.observableArrayList(abwensendeKlausurTeilnehmer);

        klausurTeilnehmerAbwesendTableView.setItems(kt);
        klausurTeilnehmerAbwesendTableView.getSortOrder().add(klausurTeilnehmerAbwesendName);
    }

}
