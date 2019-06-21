package model.relationen;

import model.klausur.Klausur;
import model.person.Student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studentTeilnahmeKlausur")
public class KlausurTeilnahme implements Serializable {

    @Id
    @ManyToOne
    private Student student;
    @Id
    @ManyToOne
    private Klausur klausur;

    private Boolean erschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme() {
    }

    public KlausurTeilnahme(Student student, Klausur klausur, Boolean erschienen, Boolean entschuldigt, Double punkte, Double note) {
        this.student = student;
        this.klausur = klausur;
        this.erschienen = erschienen;
        this.entschuldigt = entschuldigt;
        this.punkte = punkte;
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
        KlausurTeilnahme klausurTeilnahme = (KlausurTeilnahme) o;
        return Objects.equals(this.student, klausurTeilnahme.getStudent()) &&
                Objects.equals(this.klausur, klausurTeilnahme.getKlausur());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.klausur);
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

    public Klausur getKlausur() {
        return klausur;
    }

    public void setKlausur(Klausur klausur) {
        this.klausur = klausur;
    }

    public Boolean getErschienen() {
        return erschienen;
    }

    public void setErschienen(Boolean erschienen) {
        this.erschienen = erschienen;
    }

    public Boolean getEntschuldigt() {
        return entschuldigt;
    }

    public void setEntschuldigt(Boolean entschuldigt) {
        this.entschuldigt = entschuldigt;
    }

    public Double getPunkte() {
        return punkte;
    }

    public void setPunkte(Double punkte) {
        this.punkte = punkte;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
