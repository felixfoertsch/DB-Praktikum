package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import org.hibernate.SessionFactory;


public class KlausurStatistikController {
    private SessionFactory sessionFactory;
    private Klausur klausur;
    @FXML
    private GridPane klausurStatistikGridPane;


    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    void setupController(Klausur klausur) {
        this.klausur = klausur;
    }
}
