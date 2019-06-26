package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.person.FetchStudent;
import model.person.Student;
import org.hibernate.Session;
import services.HibernateService;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class TopStudentenController {
    @FXML
    TextField weightKlausur;
    @FXML
    TextField weightPraktikum;
    @FXML
    TextField weightSeminar;
    @FXML
    TextField bonus;
    @FXML
    TableView<FetchStudent> topStudentTableView;
    @FXML
    TableColumn<FetchStudent, String> topStudentId;
    @FXML
    TableColumn<FetchStudent, String> topStudentMatrNr;
    @FXML
    TableColumn<FetchStudent, String> topStudentVorname;
    @FXML
    TableColumn<FetchStudent, String> topStudentNachname;
    @FXML
    TableColumn<FetchStudent, String> topStudentUnimail;
    @FXML
    TableColumn<FetchStudent, String> topStudentScore;
    private HibernateService hibernateService;

    void setupController(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
        weightKlausur.setTextFormatter(getDecimalFormatter());
        weightPraktikum.setTextFormatter(getDecimalFormatter());
        weightSeminar.setTextFormatter(getDecimalFormatter());
        bonus.setTextFormatter(getBoundDoubleFormatter());
    }

    @FXML
    private void listTopButtonPressed() {
        if (weightKlausur.getText().isEmpty()) {
            weightKlausur.setText("1");
        }
        if (weightPraktikum.getText().isEmpty()) {
            weightPraktikum.setText("1");
        }
        if (weightSeminar.getText().isEmpty()) {
            weightSeminar.setText("1");
        }
        if (bonus.getText().isEmpty()) {
            bonus.setText("0");
        }

        Session session = hibernateService.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("SELECT * FROM topstudenten(" +
                weightKlausur.getText() + ", " +
                weightPraktikum.getText() + ", " +
                weightSeminar.getText() + ", " +
                bonus.getText() + ")", "FetchStudentMapping");

        List<FetchStudent> topStudenten = query.getResultList();

        session.getTransaction().commit();
        session.close();

        ObservableList<FetchStudent> observableTopStudenten = FXCollections.observableArrayList(topStudenten);
        topStudentTableView.setItems(observableTopStudenten);

        topStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        topStudentMatrNr.setCellValueFactory(new PropertyValueFactory<>("matrikelNr"));
        topStudentVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        topStudentNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        topStudentUnimail.setCellValueFactory(new PropertyValueFactory<>("uniMail"));
        topStudentScore.setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    private TextFormatter<Integer> getDecimalFormatter() {
        Pattern validDecimalText = Pattern.compile("\\d*");
        return new TextFormatter<>(
                new IntegerStringConverter(), 1,
                change -> {
                    if (change.getControlNewText().isEmpty()) return change;
                    String newText = change.getControlNewText();
                    if (validDecimalText.matcher(newText).matches()) {
                        if (Integer.valueOf(change.getControlNewText()) > 100) return null;
                        return change;
                    } else return null;
                });
    }

    private TextFormatter<Double> getBoundDoubleFormatter() {
        Pattern validDoubleText = Pattern.compile("((\\d*)|(\\d+\\.\\d*))");
        return new TextFormatter<>(
                new DoubleStringConverter(), 0.0,
                change -> {
                    if (change.getControlNewText().isEmpty()) return change;
                    String newText = change.getControlNewText();
                    if (validDoubleText.matcher(newText).matches()) {
                        if (Double.valueOf(change.getControlNewText()) > 1 || Double.valueOf(change.getControlNewText()) < 0 ) return null;
                        return change;
                    } else return null;
                });
    }
}
