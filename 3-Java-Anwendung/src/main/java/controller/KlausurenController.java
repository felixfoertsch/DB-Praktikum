package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.klausur.Klausur;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
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

    @FXML
    public void showDetails() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Integer selectedKlausurId = table.getSelectionModel().getSelectedItem().getId();
            Klausur selectedKlausur = session.get(Klausur.class, selectedKlausurId);
            PropertySheet propertySheet = new PropertySheet(BeanPropertyUtils.getProperties(selectedKlausur));
            klausurPropertySheet.setContent(propertySheet);
            klausurenMasterDetailPane.showDetailNodeProperty().setValue(true);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
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
}
