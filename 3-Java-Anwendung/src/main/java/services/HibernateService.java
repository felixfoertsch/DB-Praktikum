package services;

import model.klausur.Klausur;
import model.person.Student;
import model.relationen.AufgabenBearbeitung;
import model.relationen.KlausurTeilnahme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class HibernateService {
    private SessionFactory sessionFactory;

    public HibernateService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<Student> fetchStudentByMatrNr(String matrnr) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Optional<Student> optionalStudent = session.byNaturalId(Student.class).using("matrikelNr", matrnr).loadOptional();
            tx.commit();
            return optionalStudent;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw e;
        }
    }

    public KlausurTeilnahme fetchKlausurTeilnahme(Student student, Klausur klausur) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            KlausurTeilnahme kt = session.find(
                    KlausurTeilnahme.class,
                    new KlausurTeilnahme(student, klausur));
            tx.commit();
            return kt;
        } catch (Exception e) {
            System.out.println("N/A");
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Klausur> fetchKlausuren() {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            List<Klausur> k = session.createQuery("from Klausur").getResultList();
            tx.commit();
            return k;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public Klausur fetchKlausurById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Klausur k = session.byId(Klausur.class).load(id);
        session.getTransaction().commit();
        session.close();
        return k;
    }

    public void saveKlausurTeilnahme(KlausurTeilnahme klausurTeilnahme) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(klausurTeilnahme);
        session.getTransaction().commit();
        session.close();
    }

    public void saveAufgabenBearbeitung(AufgabenBearbeitung aufgabenBearbeitung) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(aufgabenBearbeitung);
        session.getTransaction().commit();
        session.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
