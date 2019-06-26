package model.person;

import model.relationen.AufgabenBearbeitung;
import model.relationen.KlausurTeilnahme;
import model.relationen.SemPrakTeilnahme;
import model.relationen.Studium;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NaturalId
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;

    @OneToMany(mappedBy = "student")
    private List<Studium> studien;

    @OneToMany(mappedBy = "student")
    private List<KlausurTeilnahme> klausurTeilnahmen;

    @OneToMany(mappedBy = "student")
    private List<AufgabenBearbeitung> aufgabenBearbeitungen;

    @OneToMany(mappedBy = "student")
    private List<SemPrakTeilnahme> semPrakTeilnahmen;

    @Transient
    private Float score;

    public Student() {
    }

    public Student(String matrikelNr, String vorname, String nachname, String uniMail, Float score) {
        this.matrikelNr = matrikelNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.uniMail = uniMail;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(this.id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(String matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUniMail() {
        return uniMail;
    }

    public void setUniMail(String uniMail) {
        this.uniMail = uniMail;
    }

    public List<Studium> getStudien() {
        return studien;
    }

    public void setStudien(List<Studium> studien) {
        this.studien = studien;
    }

    public List<KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public void setKlausurTeilnahmen(List<KlausurTeilnahme> klausurTeilnahmen) {
        this.klausurTeilnahmen = klausurTeilnahmen;
    }

    public List<AufgabenBearbeitung> getAufgabenBearbeitungen() {
        return aufgabenBearbeitungen;
    }

    public void setAufgabenBearbeitungen(List<AufgabenBearbeitung> aufgabenBearbeitungen) {
        this.aufgabenBearbeitungen = aufgabenBearbeitungen;
    }

    public List<SemPrakTeilnahme> getSemPrakTeilnahmen() {
        return semPrakTeilnahmen;
    }

    public void setSemPrakTeilnahmen(List<SemPrakTeilnahme> semPrakTeilnahmeen) {
        this.semPrakTeilnahmen = semPrakTeilnahmeen;
    }

    @Transient
    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
