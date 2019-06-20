package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.Aufgabe;
import model.Raum;
import model.Studiengang;
import model.klausur.Klausur;
import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;
import model.veranstaltung.Veranstaltung;
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
        Studiengang s = session.load(Studiengang.class, 2);
        System.out.println(s.getName());


        List studien = session.createQuery("from AufgabenBearbeitung ").list();

        Klausur k = session.load(Klausur.class, 2);
        for (Aufgabe a : k.getAufgaben()) {
            System.out.println(a.getKlausur());
        }
        for (Raum r : k.getRaum()) {
            System.out.println(r.getBezeichnung());
            for (Mitarbeiter m : r.getMitarbeiter()) {
                System.out.println(m.getNachname());
            }
        }

        Mitarbeiter m = session.load(Mitarbeiter.class, 1);
        System.out.println(m.getNachname());

        List<Klausur> klausuren = session.createQuery("from Klausur").list();

        for (Klausur klausur : klausuren) {
            for (Raum r : klausur.getRaum()) {
                System.out.println(r.getBezeichnung());
            }

            for (Mitarbeiter aufsicht : klausur.getAufsichten()) {
                System.out.println(aufsicht.getNachname());
            }
        }

        List<VeranstaltungAbhaltung> va = session.createQuery("from VeranstaltungAbhaltung ").list();

        for (VeranstaltungAbhaltung v : va) {
            System.out.println(v.getWochentag());
            System.out.println(v.getVeranstaltung().getName());
        }

        List<Mitarbeiter> mitarbeiters = session.createQuery("from Mitarbeiter").list();
        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            System.out.println(mitarbeiter.getNachname() + ": \n");
            for (Veranstaltung v : mitarbeiter.getVeranstaltungen()) {
                System.out.println(v.getName());
            }
        }


        System.out.println("STOP");

        session.close();
    }
}
