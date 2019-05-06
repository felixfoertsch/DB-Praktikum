package model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;

    @OneToMany(mappedBy = "student")
    @MapKey(name = "klausurTeilnahmeKey")
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;

//    @OneToMany(mappedBy = "student")
//    @MapKey(name = "semPrakTeilnahmeKey")
//    private Map<String, PraktikumTeilnahme> praktikumTeilnahme;
    private Map<String, SemPrakTeilnahme> semPrakTeilnahme;

    //Maybe change to List
    private Map<String, Studium> studiumMap;

    @OneToMany(mappedBy = "student")
    @MapKey(name = "aufgabenBearbeitungKey")
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Student(String matrikelNr, String vorname, String nachname, String uniMail, Map<String, Studium> studiumMap) {
        this.klausurTeilnahmen = new HashMap<>();
        this.semPrakTeilnahme = new HashMap<>();
        this.aufgabenBearbeitungen = new HashMap<>();
        this.matrikelNr = matrikelNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.uniMail = uniMail;
        this.studiumMap = studiumMap;
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

    public Map<String, Studium> getStudiumMap() {
        return studiumMap;
    }

    public void setStudiumMap(Map<String, Studium> studiumMap) {
        this.studiumMap = studiumMap;
    }

    public Map<String, KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public void setKlausurTeilnahmen(Map<String, KlausurTeilnahme> klausurTeilnahmen) {
        this.klausurTeilnahmen = klausurTeilnahmen;
    }

    public Map<String, SemPrakTeilnahme> getSemPrakTeilnahme() {
        return semPrakTeilnahme;
    }

    public void setSemPrakTeilnahme(Map<String, SemPrakTeilnahme> semPrakTeilnahme) {
        this.semPrakTeilnahme = semPrakTeilnahme;
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

    public void addPrakTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahme.put(pt.generatePrakKey(), pt);
    }

    public void addSemTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahme.put(pt.generateSemKey(), pt);
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
                ", studiumMap=" + studiumMap +
                ", klausurTeilnahmen=" + klausurTeilnahmen +
                ", semPrakTeilnahme=" + semPrakTeilnahme +
                ", aufgabenBearbeitungen=" + aufgabenBearbeitungen +
                '}';
    }
}
