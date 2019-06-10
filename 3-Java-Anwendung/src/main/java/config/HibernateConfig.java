package config;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateConfig {
    private static SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration c = new Configuration();

                Properties p = new Properties();
                p.put(Environment.DRIVER, "org.postgresql.Driver");
                p.put(Environment.URL, "jdbc:postgresql://localhost:5432/dbp");
                p.put(Environment.USER, "dbp");
                p.put(Environment.PASS, "dbp");
                p.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");

                p.put(Environment.SHOW_SQL, "true");

                p.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                //p.put(Environment.HBM2DDL_AUTO, "create-drop");
                p.put(Environment.HBM2DDL_AUTO, "none");

                c.setProperties(p);

                c.addAnnotatedClass(Veranstaltung.class);
                c.addAnnotatedClass(Abschlussklausur.class);
                c.addAnnotatedClass(Aufgabe.class);
                c.addAnnotatedClass(AufgabenBearbeitung.class);
                c.addAnnotatedClass(Grundvorlesung.class);
                c.addAnnotatedClass(Klausur.class);
                c.addAnnotatedClass(KlausurTeilnahme.class);
                c.addAnnotatedClass(Mitarbeiter.class);
                c.addAnnotatedClass(Oberseminar.class);
                c.addAnnotatedClass(Praktikum.class);
                c.addAnnotatedClass(Problemseminar.class);
                c.addAnnotatedClass(Raum.class);
                c.addAnnotatedClass(Seminar.class);
                c.addAnnotatedClass(SemPrakTeilnahme.class);
                c.addAnnotatedClass(Spezialvorlesung.class);
                c.addAnnotatedClass(Student.class);
                c.addAnnotatedClass(Studiengang.class);
                c.addAnnotatedClass(Studium.class);
                c.addAnnotatedClass(Uebung.class);
                c.addAnnotatedClass(Wiederholungsklausur.class);
                c.addAnnotatedClass(Zwischenklausur.class);

                ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();

                sessionFactory = c.buildSessionFactory(sr);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
