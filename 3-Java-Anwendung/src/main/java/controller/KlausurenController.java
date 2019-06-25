package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import model.person.Student;
import model.relationen.KlausurTeilnahme;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    BorderPane klausurNotenEingabeBorderPane;
    @FXML
    GridPane klausurNotenEingabeSucheGridPane;
    @FXML
    TextField matrNrSucheTextField;
    @FXML
    GridPane klausurNotenEingabeGridPane;
    @FXML
    Label klausurNotenEingabeVorname;
    @FXML
    Label klausurNotenEingabeNachname;
    @FXML
    Label klausurNotenEingabeMatrNr;
    @FXML
    Label klausurNotenEingabeInfo;


    private Klausur selectedKlausur;
    private Student selectedStudent;


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
        setupPropertyTab(klausur);
        setupTeilnehmerTab(klausur);
        setupNotenEingabeTab(klausur);
        klausurenMasterDetailPane.showDetailNodeProperty().setValue(true);
    }

    private void setupPropertyTab(Klausur klausur) {
        PropertySheet propertySheet = new PropertySheet(BeanPropertyUtils.getProperties(klausur));
        klausurPropertySheet.setContent(propertySheet);
    }

    private void setupTeilnehmerTab(Klausur klausur) {

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

    private void setupNotenEingabeTab(Klausur klausur) {
        klausurNotenEingabeVorname.setText("");
        klausurNotenEingabeNachname.setText("");
        klausurNotenEingabeMatrNr.setText("");
        klausurNotenEingabeInfo.setText("");
        klausurNotenEingabeBorderPane.setBottom(null);

        // Make TextField numeric only
        matrNrSucheTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                matrNrSucheTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void suchenButtonPressed() {
        if (matrNrSucheTextField.getText().isEmpty()) {
            return;
        }
        Optional<Student> optionalStudent = fetchStudentByMatrNr(matrNrSucheTextField.getText());
        if (optionalStudent.isEmpty()) {
            System.out.println("No Student with this MatrNr");
            return;
        }

        selectedStudent = optionalStudent.get();

        klausurNotenEingabeVorname.setText(selectedStudent.getVorname());
        klausurNotenEingabeNachname.setText(selectedStudent.getNachname());
        klausurNotenEingabeMatrNr.setText(selectedStudent.getMatrikelNr());

        KlausurTeilnahme kt = fetchKlausurTeilnahme(selectedStudent, selectedKlausur);
        if (kt == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurTeilnahmeEingabe.fxml"));
                GridPane gridPane = loader.load();

                KlausurTeilnahmeEingabeController ktec = loader.getController();
                ktec.injectSessionFactory(sessionFactory);
                ktec.setupController(selectedStudent, selectedKlausur);

                klausurNotenEingabeBorderPane.setBottom(gridPane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Optional<Student> fetchStudentByMatrNr(String matrnr) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Optional<Student> optionalStudent = session.byNaturalId(Student.class).using("matrikelNr", matrnr).loadOptional();
            tx.commit();
            return optionalStudent;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    private KlausurTeilnahme fetchKlausurTeilnahme(Student student, Klausur klausur) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            KlausurTeilnahme kt = session.find(
                    KlausurTeilnahme.class,
                    new KlausurTeilnahme(student, klausur));
            tx.commit();
            return kt;
        } catch (Exception e) {
            System.out.println("N/A");
            if (tx != null) tx.rollback();
            throw e;
        }
    }

}
