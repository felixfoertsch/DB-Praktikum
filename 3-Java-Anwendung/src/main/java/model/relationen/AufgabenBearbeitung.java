package model.relationen;

import model.Aufgabe;
import model.person.Student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bearbeitung")
public class AufgabenBearbeitung implements Serializable {

    @Id
    @ManyToOne
    private Student student;
    @Id
    @ManyToOne
    private Aufgabe aufgabe;
    private Double punkte;

    public AufgabenBearbeitung() {
    }

    public AufgabenBearbeitung(Student student, Aufgabe aufgabe, Double punkte) {
        this.student = student;
        this.aufgabe = aufgabe;
        this.punkte = punkte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AufgabenBearbeitung aufgabenBearbeitung = (AufgabenBearbeitung) o;
        return Objects.equals(this.student, aufgabenBearbeitung.getStudent()) &&
                Objects.equals(this.aufgabe, aufgabenBearbeitung.getAufgabe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.aufgabe);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Aufgabe getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(Aufgabe aufgabe) {
        this.aufgabe = aufgabe;
    }

    public Double getPunkte() {
        return punkte;
    }

    public void setPunkte(Double punkte) {
        this.punkte = punkte;
    }
}
