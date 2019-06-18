package controller;

import model.klausur.Klausur;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.Studiengang;
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
        Klausur k = session.load(Klausur.class, 1);

        System.out.println(k.getDatum());
        session.close();
    }
}
