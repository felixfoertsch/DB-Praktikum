package controller;

import dataimport.ImporterImpl;
import dataimport.model.Universitaet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Studiengang;
import model.klausur.Klausur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


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
        Pane pane = loader.load();

        KlausurenController kc = loader.getController();
        kc.injectSessionFactory(sessionFactory);

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
    public void newVeranstaltungButtonClicked(Event e) {
        VeranstaltungController vc = new VeranstaltungController();
        vc.hello();
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
        var studiengang = new Studiengang();
        studiengang.setName("Test");
        studiengang.setAbschluss("Testabschluss");
        studiengang.setRegelstudienzeit(111);

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
}
