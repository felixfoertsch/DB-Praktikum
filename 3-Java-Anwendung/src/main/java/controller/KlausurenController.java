package controller;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import model.relationen.KlausurTeilnahme;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import model.klausur.Klausur;
import model.person.Student;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.control.table.TableRowExpanderColumn;
import org.controlsfx.property.BeanPropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class KlausurenController {

    @FXML
    MasterDetailPane klausurenMasterDetailPane;
    @FXML
    TabPane klausurTabPane;
    @FXML
    Tab klausurPropertySheet;
    @FXML
    TableView<Klausur> table;
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

    @FXML
    TableView<KlausurTeilnahme> klausurTeilnehmer;
    @FXML
    TableColumn<KlausurTeilnahme, String> name;
    @FXML
    TableColumn<KlausurTeilnahme, String> matrnr;
    @FXML
    TableColumn<KlausurTeilnahme, String> note;
    @FXML
    TableColumn<KlausurTeilnahme, String> punkte;


    private SessionFactory sessionFactory;
    private List<Klausur> klausuren;

    public KlausurenController() {

    }

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    void populateTable() {
        fetchKlausuren();
        ObservableList<Klausur> klausuren = FXCollections.observableArrayList(this.klausuren);
        table.setItems(klausuren);
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
        table.getSortOrder().add(date);
    }

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
    public void showDetails() {
        Klausur selectedKlausur = fetchKlausurByRowSelection();
        showPropertyTab(selectedKlausur);
        showTeilnehmerTab(selectedKlausur);

        klausurenMasterDetailPane.showDetailNodeProperty().setValue(true);
    }

    private Klausur fetchKlausurByRowSelection() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Integer selectedKlausurId = table.getSelectionModel().getSelectedItem().getId();
            Klausur selectedKlausur = session.get(Klausur.class, selectedKlausurId);
            tx.commit();
            return selectedKlausur;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    private void showPropertyTab(Klausur klausur) {
        PropertySheet propertySheet = new PropertySheet(BeanPropertyUtils.getProperties(klausur));
        klausurPropertySheet.setContent(propertySheet);
    }

    private void showTeilnehmerTab(Klausur klausur) {

        ObservableList<KlausurTeilnahme> kt = FXCollections.observableArrayList(klausur.getKlausurTeilnahmen());
        klausurTeilnehmer.setItems(kt);
        name.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getVorname() + " " + l.getValue().getStudent().getNachname());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        matrnr.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getStudent().getMatrikelNr());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        note.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getNote().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });
        punkte.setCellValueFactory(l -> {
            if (l.getValue() != null && l.getValue().getStudent() != null) {
                return new SimpleStringProperty(l.getValue().getPunkte().toString());
            } else {
                return new SimpleStringProperty("N/A");
            }
        });

        TableRowExpanderColumn<KlausurTeilnahme> expander = new TableRowExpanderColumn<>(param -> {
            HBox editor = new HBox(10);
            TextField text = new TextField(param.getValue().getPunkte().toString());
            Button save = new Button("Save");
            save.setOnAction(event -> {
                savePunkte(text.getText(), param.getValue());
                param.toggleExpanded();
            });
            editor.getChildren().addAll(text, save);
            return editor;
        });

        klausurTeilnehmer.getColumns().add(expander);

        klausurTeilnehmer.getSortOrder().add(name);
    }

    private void savePunkte(String punkteString, KlausurTeilnahme kt) {
        Double punkte = new Double(punkteString);
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            kt.setPunkte(punkte);
            session.update(kt);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }


}
