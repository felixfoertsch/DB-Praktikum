package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.klausur.Klausur;
import model.person.Student;
import model.relationen.KlausurTeilnahme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class KlausurTeilnahmeEingabeController {
    private SessionFactory sessionFactory;
    private Student selectedStudent;
    private Klausur selectedKlausur;

    // Form: KlausurTeilnahmeHinzufuegen
    @FXML
    CheckBox klausurNotenEingabeErschienenCheckBox;
    @FXML
    CheckBox klausurNotenEingabeEntschuldigtCheckBox;
    @FXML
    TextField klausurNotenEingabePunkteTextField;
    @FXML
    TextField klausurNotenEingabeNotenTextField;
    @FXML
    Button hinzufuegenButton;

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    void injectKlausurTeilnahmeProperties(Student student, Klausur klausur) {
        this.selectedStudent = student;
        this.selectedKlausur = klausur;
    }

    @FXML
    public void hinzufuegenButtonPressed() {
        KlausurTeilnahme kt = new KlausurTeilnahme();
        kt.setEntschuldigt(klausurNotenEingabeEntschuldigtCheckBox.isSelected());
        kt.setErschienen(klausurNotenEingabeErschienenCheckBox.isSelected());
        kt.setKlausur(selectedKlausur);
        kt.setStudent(selectedStudent);
        kt.setPunkte(Double.valueOf(klausurNotenEingabePunkteTextField.getText()));
        kt.setNote(Double.valueOf(klausurNotenEingabeNotenTextField.getText()));

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(kt);
        session.getTransaction().commit();
        session.close();
    }
}
