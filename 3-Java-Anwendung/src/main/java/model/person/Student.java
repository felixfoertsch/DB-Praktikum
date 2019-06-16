package model.person;

import model.relationen.AufgabenBearbeitung;
import model.relationen.KlausurTeilnahme;
import model.relationen.SemPrakTeilnahme;
import model.relationen.Studium;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    @NaturalId
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;

    @OneToMany
    private Collection<KlausurTeilnahme> klausurTeilnahmen;
    @OneToMany
    private Collection<SemPrakTeilnahme> semPrakTeilnahmen;
    @OneToMany
    private Collection<Studium> studien;
    @OneToMany
    private Collection<AufgabenBearbeitung> aufgabenBearbeitungen;

    public Student() {
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

    public Collection<KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public void setKlausurTeilnahmen(Collection<KlausurTeilnahme> klausurTeilnahmen) {
        this.klausurTeilnahmen = klausurTeilnahmen;
    }

    public Collection<SemPrakTeilnahme> getSemPrakTeilnahmen() {
        return semPrakTeilnahmen;
    }

    public void setSemPrakTeilnahmen(Collection<SemPrakTeilnahme> semPrakTeilnahme) {
        this.semPrakTeilnahmen = semPrakTeilnahme;
    }

    public Collection<Studium> getStudien() {
        return studien;
    }

    public void setStudien(Collection<Studium> studiumMap) {
        this.studien = studiumMap;
    }

    public Collection<AufgabenBearbeitung> getAufgabenBearbeitungen() {
        return aufgabenBearbeitungen;
    }

    public void setAufgabenBearbeitungen(Collection<AufgabenBearbeitung> aufgabenBearbeitungen) {
        this.aufgabenBearbeitungen = aufgabenBearbeitungen;
    }
}
