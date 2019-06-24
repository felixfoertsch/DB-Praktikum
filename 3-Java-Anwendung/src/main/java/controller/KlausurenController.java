package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.relationen.KlausurTeilnahme;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import model.klausur.Klausur;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.table.TableRowExpanderColumn;
import org.controlsfx.property.BeanPropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class KlausurenController {

    @FXML
    MasterDetailPane klausurenMasterDetailPane;
    // Master
    @FXML
    TableView<Klausur> klausurTableView;
    @FXML
    TableColumn<Klausur, Integer> id;
    @FXML
    TableColumn<Klausur, String> va;
    @FXML
    TableColumn<Klausur, LocalDate> date;
    @FXML
    TableColumn<Klausur, LocalTime> time;
    @FXML
    TableColumn<Klausur, Double> points;

    // Detail
    @FXML
    TabPane klausurTabPane;

    // Tab: Details
    @FXML
    Tab klausurPropertySheet;

    // Tab: Teilnehmer
    @FXML
    Tab klausurTeilnehmerTab;
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

    private SessionFactory sessionFactory;
    private List<Klausur> klausuren;

    public KlausurenController() {

    }

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    void configureAndPopulateMaster() {
        fetchKlausuren();
        ObservableList<Klausur> klausuren = FXCollections.observableArrayList(this.klausuren);
        klausurTableView.setItems(klausuren);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("datum"));
        time.setCellValueFactory(new PropertyValueFactory<>("uhrzeitVon"));
        points.setCellValueFactory(new PropertyValueFactory<>("gesamtpunktzahl"));

        va.setCellValueFactory(klausurStringCellDataFeatures -> {
            if (klausurStringCellDataFeatures.getValue() != null && klausurStringCellDataFeatures.getValue().getGrundvorlesung() != null) {
                return new SimpleStringProperty(klausurStringCellDataFeatures.getValue().getGrundvorlesung().getName());
            } else if (klausurStringCellDataFeatures.getValue() != null && klausurStringCellDataFeatures.getValue().getSpezialvorlesung() != null) {
                return new SimpleStringProperty(klausurStringCellDataFeatures.getValue().getSpezialvorlesung().getName());
            } else {
                return new SimpleStringProperty("<Keine zugehörige VA>");
            }
        });
        klausurTableView.getSortOrder().add(date);

        // Listener for the selected row!
        klausurTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                configureDetail(newSelection);
            }
        });
    }

    @Transactional
    private void fetchKlausuren() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            this.klausuren = session.createQuery("from Klausur").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    @FXML
    private void configureDetail(Klausur klausur) {
        setPropertyTab(klausur);
        setTeilnehmerTab(klausur);

        klausurenMasterDetailPane.showDetailNodeProperty().setValue(true);
    }

    private void setPropertyTab(Klausur klausur) {
        PropertySheet propertySheet = new PropertySheet(BeanPropertyUtils.getProperties(klausur));
        klausurPropertySheet.setContent(propertySheet);
    }

    private void setTeilnehmerTab(Klausur klausur) {

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
        klausurTeilnehmerTab.setContent(klausurTeilnehmerTableView);
    }

}
