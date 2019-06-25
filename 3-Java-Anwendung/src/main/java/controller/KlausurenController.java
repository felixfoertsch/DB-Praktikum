package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import model.person.Student;
import model.relationen.KlausurTeilnahme;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


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

    // Tab: Noteneingabe
    @FXML
    Tab klausurNotenEingabe;
    @FXML
    GridPane klausurNotenEingabeSucheGridPane;
    @FXML
    TextField matrNrSucheTextField;
    @FXML
    GridPane klausurNotenEingabeGridPane;
    Klausur selectedKlausur;

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

        klausurTeilnehmerTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("BUH");
            }
        });
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
    private void configureDetail(Klausur klausur) {
        selectedKlausur = klausur;
        setPropertyTab(klausur);
        setTeilnehmerTab(klausur);
        setNotenEingabeTab(klausur);
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

    private void setNotenEingabeTab(Klausur klausur) {

        // Make TextField numeric only
        matrNrSucheTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                matrNrSucheTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void suchenButtonPressed() {
        if (matrNrSucheTextField.getText().isEmpty()) return;
        Optional<Student> student = fetchStudentByMatrNr(matrNrSucheTextField.getText());
        student.ifPresent(value -> System.out.println(value.getNachname()));
        Integer klausurId = selectedKlausur.getId();
    }

    private Optional<Student> fetchStudentByMatrNr(String matrnr) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Optional<Student> optionalStudent = session.byNaturalId(Student.class).using( "matrikelNr", matrnr ).loadOptional();
            tx.commit();
            return optionalStudent;
        } catch (Exception e) {
            System.out.println("No Student with this MatrNr");
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

}
