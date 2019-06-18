package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.person.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class KlausurenController {
    @FXML
    Pane klausurenPane;
    @FXML
    TableView tableView;

    private SessionFactory sessionFactory;

    public KlausurenController() {

    }

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    void populateTable() {
        Session session = sessionFactory.openSession();
        Student k = session.load(Student.class, 2);

        System.out.println(k.getMatrikelNr());
        session.close();
    }
}
