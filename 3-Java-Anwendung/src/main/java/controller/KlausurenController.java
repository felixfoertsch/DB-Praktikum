package controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.klausur.Klausur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;


public class KlausurenController implements Initializable {

    @FXML
    Pane klausurenPane;
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

    public KlausurenController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    void populateTable() {
        Session session = sessionFactory.openSession();
        List<Klausur> klausurenData = session.createQuery("from Klausur").list();
        ObservableList<Klausur> klausuren = FXCollections.observableArrayList(klausurenData);
        table.setItems(klausuren);
        id.setCellValueFactory(new PropertyValueFactory<Klausur, Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Klausur, LocalDate>("datum"));
        time.setCellValueFactory(new PropertyValueFactory<Klausur, LocalTime>("uhrzeitVon"));
        points.setCellValueFactory(new PropertyValueFactory<Klausur, Double>("gesamtpunktzahl"));

        va.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Klausur, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Klausur, String> k) {
                if (k.getValue() != null && k.getValue().getGrundvorlesung() != null) {
                    return new SimpleStringProperty(k.getValue().getGrundvorlesung().getName());
                } else if (k.getValue() != null && k.getValue().getSpezialvorlesung() != null) {
                    return new SimpleStringProperty(k.getValue().getSpezialvorlesung().getName());
                } else {
                    return new SimpleStringProperty("<Keine zugehörige VA>");
                }
            }
        });
    }
}
