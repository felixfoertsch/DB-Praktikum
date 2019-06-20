package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.Studiengang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


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
        Studiengang k = session.load(Studiengang.class, 1);
        System.out.println(k.getName());


        List studien = session.createQuery("from AufgabenBearbeitung ").list();
        System.out.println("STOP");

        session.close();
    }
}
