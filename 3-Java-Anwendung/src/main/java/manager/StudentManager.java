package manager;

import config.HibernateConfig;
import model.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentManager {


    public void saveStudent(Student s) {
        Transaction t = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            t = session.beginTransaction();
            session.save(s);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                //t.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Student> getStudents() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        }
    }
}
