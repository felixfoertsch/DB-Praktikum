package model.relationen;

import model.person.Student;
import model.veranstaltung.Veranstaltung;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class SemPrakTeilnahme implements Serializable {

    @Id
    @ManyToOne
    private Student student;
    @Id
    @ManyToOne
    private Veranstaltung veranstaltung;
    private Double note;

    public SemPrakTeilnahme() {
    }

    public SemPrakTeilnahme(Student student, Veranstaltung veranstaltung, Double note) {
        this.student = student;
        this.veranstaltung = veranstaltung;
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SemPrakTeilnahme semPrakTeilnahme = (SemPrakTeilnahme) o;
        return Objects.equals(this.student, semPrakTeilnahme.getStudent()) &&
                Objects.equals(this.veranstaltung, semPrakTeilnahme.getVeranstaltung());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.veranstaltung);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }
}
