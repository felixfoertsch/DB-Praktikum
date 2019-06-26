package controller;

import model.klausur.Klausur;
import org.hibernate.SessionFactory;

public class KlausurStatistikController {
    private SessionFactory sessionFactory;
    private Klausur klausur;

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    void setupController(Klausur klausur) {
        this.klausur = klausur;
    }
}
