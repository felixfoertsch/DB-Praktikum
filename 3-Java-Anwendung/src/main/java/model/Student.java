package model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;
    private Studium studium;

    @OneToMany(mappedBy = "student")
    @MapKey(name = "klausurTeilnahmeKey")
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;

    @OneToMany(mappedBy = "student")
    @MapKey(name = "praktikumTeilnahmeKey")
    private Map<String, PraktikumTeilnahme> praktikumTeilnahme;

    @OneToMany(mappedBy = "student")
    @MapKey(name = "aufgabenBearbeitungKey")
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Student(String matrikelNr, String vorname, String nachname, String uniMail, Studium studium) {
        this.klausurTeilnahmen = new HashMap<>();
        this.praktikumTeilnahme = new HashMap<>();
        this.aufgabenBearbeitungen = new HashMap<>();
        this.matrikelNr = matrikelNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.uniMail = uniMail;
        this.studium = studium;
    }

    public Student() {
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getUniMail() {
        return uniMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setMatrikelNr(String matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setUniMail(String uniMail) {
        this.uniMail = uniMail;
    }

    public Studium getStudium() {
        return studium;
    }

    public void setStudium(Studium studium) {
        this.studium = studium;
    }

    public Map<String, KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public void setKlausurTeilnahmen(Map<String, KlausurTeilnahme> klausurTeilnahmen) {
        this.klausurTeilnahmen = klausurTeilnahmen;
    }

    public Map<String, PraktikumTeilnahme> getPraktikumTeilnahme() {
        return praktikumTeilnahme;
    }

    public void setPraktikumTeilnahme(Map<String, PraktikumTeilnahme> praktikumTeilnahme) {
        this.praktikumTeilnahme = praktikumTeilnahme;
    }

    public Map<String, AufgabenBearbeitung> getAufgabenBearbeitungen() {
        return aufgabenBearbeitungen;
    }

    public void setAufgabenBearbeitungen(Map<String, AufgabenBearbeitung> aufgabenBearbeitungen) {
        this.aufgabenBearbeitungen = aufgabenBearbeitungen;
    }

    public void addKlausurTeilnahme(KlausurTeilnahme kt) {
        this.klausurTeilnahmen.put(kt.generateKey(), kt);
    }

    public void addPraktikumTeilnahme(PraktikumTeilnahme pt) {
        this.praktikumTeilnahme.put(pt.generateKey(), pt);
    }

    public void addAufgabenBearbeitung(AufgabenBearbeitung ab) {
        this.aufgabenBearbeitungen.put(ab.generateKey(), ab);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", matrikelNr='" + matrikelNr + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", uniMail='" + uniMail + '\'' +
                ", studium=" + studium +
                ", klausurTeilnahmen=" + klausurTeilnahmen +
                ", praktikumTeilnahme=" + praktikumTeilnahme +
                ", aufgabenBearbeitungen=" + aufgabenBearbeitungen +
                '}';
    }
}
