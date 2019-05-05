package model;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;
    //Maybe change to List
    private Map<String, Studium> studiumMap;
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;
    private Map<String, SemPrakTeilnahme> semPrakTeilnahme;
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

    public void addKlausurTeilnahme(KlausurTeilnahme kt) {
        this.klausurTeilnahmen.put(kt.generateKey(), kt);
    }

    public void addPrakTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahme.put(pt.generatePrakKey(), pt);
    }

    public void addSemTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahme.put(pt.generateSemKey(), pt);
    }

    public void addAufgabenBearbeitung(AufgabenBearbeitung ab) { this.aufgabenBearbeitungen.put(ab.generateKey(), ab); }
}
