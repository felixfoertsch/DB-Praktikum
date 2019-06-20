package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.Aufgabe;
import model.Raum;
import model.klausur.Klausur;
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
//        Studiengang s = session.load(Studiengang.class, 2);
//        System.out.println(s.getName());
//
//
//        List studien = session.createQuery("from AufgabenBearbeitung ").list();
//
        Klausur k = session.load(Klausur.class, 2);
        for (Aufgabe a : k.getAufgaben()) {
            System.out.println(a.getKlausur());
        }
        for (Raum r : k.getRaum()) {
            System.out.println(r.getBezeichnung());
        }

        System.out.println("STOP");

        session.close();
    }
}
