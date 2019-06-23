package controller;

import dataimport.ImporterImpl;
import dataimport.model.Universitaet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Aufgabe;
import model.Raum;
import model.Studiengang;
import model.klausur.Klausur;
import model.person.Mitarbeiter;
import model.person.Student;
import model.relationen.SemPrakTeilnahme;
import model.relationen.VeranstaltungAbhaltung;
import model.veranstaltung.Veranstaltung;
import org.controlsfx.control.MasterDetailPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;


public class MainController {

    private SessionFactory sessionFactory;

    @FXML
    private Stage mainStage;
    @FXML
    private MenuBar mainMenuBar;
    @FXML
    private BorderPane mainBorderPane;


    public MainController() {

    }

    public void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @FXML
    private void viewKlausuren() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Klausuren.fxml"));
        MasterDetailPane pane = loader.load();

        KlausurenController kc = loader.getController();
        kc.injectSessionFactory(sessionFactory);
        kc.populateTable();

        mainBorderPane.setCenter(pane);
    }

    @FXML
    public void importButtonClicked(Event e) {
        var importer = new ImporterImpl();
        var universitaet = new Universitaet();
        try {
            var files = importer.importCSVtoMemory();
            universitaet = importer.parseCSVandCreateModel(files);
            importer.checkMultiplicities(universitaet);
            importer.persistModel(universitaet);
        } catch (NullPointerException npe) {
            System.out.println("Import canceled.");
        }
    }


    @FXML
    public void newKlausurButtonClicked(Event e) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Klausur k = session.load(Klausur.class, 1);
        session.close();

        System.out.println(k.getDatum());
    }

    @FXML
    public void aboutButtonClicked(Event e) {
        var vBox = new VBox(new Label("Version: Aufgabe 3"));
        vBox.setPrefHeight(50);
        vBox.setPrefWidth(200);
        var scene = new Scene(vBox);
        var stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void testStudiengangInsert() {
        var studiengang = new Studiengang("Test", "Super-Abschluss", 5);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(studiengang);
        session.getTransaction().commit();
        session.close();
    }

    @FXML
    public void testStudiengangGet() {
        Session session = sessionFactory.openSession();
        Studiengang sg = session.load(Studiengang.class, 1);

        System.out.println(sg.getName());
        session.close();
    }

    @FXML
    public void testEverything() {
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

        List<Mitarbeiter> mitarbeiters = session.createQuery("from Mitarbeiter").list();
        for (Mitarbeiter mitarbeiter : mitarbeiters) {
            System.out.println(mitarbeiter.getNachname() + ": \n");
            for (Veranstaltung v : mitarbeiter.getVeranstaltungen()) {
                System.out.println(v.getName());
            }
        }

        List<Student> studies = session.createQuery("from Student").list();
        for (Student student : studies) {
            for (SemPrakTeilnahme semPrakTeilnahme : student.getSemPrakTeilnahmen()) {
                System.out.println(student.getNachname() + " " + semPrakTeilnahme.getNote());
            }
        }

        List<VeranstaltungAbhaltung> va = session.createQuery("from VeranstaltungAbhaltung").list();

        for (VeranstaltungAbhaltung v : va) {
            System.out.println(v.getWochentag());
            System.out.println(v.getVeranstaltung().getName());
        }

        System.out.println("STOP");

        session.close();
    }
}
