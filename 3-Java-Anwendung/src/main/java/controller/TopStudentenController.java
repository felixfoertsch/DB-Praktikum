package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.person.Student;
import org.hibernate.Session;
import services.HibernateService;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Arrays;
import java.util.List;


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
    TableView<Student> topStudentTableView;
    @FXML
    TableColumn<Student, String> topStudentId;
    @FXML
    TableColumn<Student, String> topStudentMatrNr;
    @FXML
    TableColumn<Student, String> topStudentVorname;
    @FXML
    TableColumn<Student, String> topStudentNachname;
    @FXML
    TableColumn<Student, String> topStudentUnimail;
    @FXML
    TableColumn<Student, String> topStudentScore;
    private HibernateService hibernateService;

    void setupController(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
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

        StoredProcedureQuery query = session.createStoredProcedureQuery("topstudenten", Student.class);
        query.registerStoredProcedureParameter(1, int.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(2, int.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(3, int.class, ParameterMode.IN);
        query.registerStoredProcedureParameter(4, double.class, ParameterMode.IN);

        query.setParameter(1, Integer.parseInt(weightKlausur.getText()));
        query.setParameter(2, Integer.parseInt(weightPraktikum.getText()));
        query.setParameter(3, Integer.parseInt(weightSeminar.getText()));
        query.setParameter(4, Double.parseDouble(bonus.getText()));

        List<Student> topStudenten = query.getResultList();

        System.out.println(Arrays.toString(topStudenten.toArray()));
        session.getTransaction().commit();
        session.close();

        ObservableList<Student> observableTopStudenten = FXCollections.observableArrayList(topStudenten);
        topStudentTableView.setItems(observableTopStudenten);

        topStudentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        topStudentMatrNr.setCellValueFactory(new PropertyValueFactory<>("matrikelNr"));
        topStudentVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        topStudentNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        topStudentUnimail.setCellValueFactory(new PropertyValueFactory<>("uniMail"));
        topStudentScore.setCellValueFactory(new PropertyValueFactory<>("score"));
    }
}
