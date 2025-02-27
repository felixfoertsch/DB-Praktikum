package dataimport.model;

import java.util.HashMap;
import java.util.Map;

public class Student {
    Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;
    private Studium studium;
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;
    private Map<String, PraktikumTeilnahme> praktikumTeilnahme;
    private Map<String, SeminarTeilnahme> seminarTeilnahme;
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Student(String matrikelNr, String vorname, String nachname, String uniMail, Studium studium) {
        this.klausurTeilnahmen = new HashMap<>();
        this.praktikumTeilnahme = new HashMap<>();
        this.seminarTeilnahme = new HashMap<>();
        this.aufgabenBearbeitungen = new HashMap<>();
        this.matrikelNr = matrikelNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.uniMail = uniMail;
        this.studium = studium;
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }

    public void addKlausurTeilnahme(KlausurTeilnahme kt) {
        this.klausurTeilnahmen.put(kt.generateKey(), kt);
    }

    public void addPraktikumTeilnahme(PraktikumTeilnahme pt) {
        this.praktikumTeilnahme.put(pt.generateKey(), pt);
    }

    public void addSeminarTeilnahme(SeminarTeilnahme st) {
        this.seminarTeilnahme.put(st.generateKey(), st);
    }

    public void addAufgabenBearbeitung(AufgabenBearbeitung ab) {
        this.aufgabenBearbeitungen.put(ab.generateKey(), ab);
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

    public Studium getStudium() {
        return studium;
    }

    public Map<String, AufgabenBearbeitung> getAufgabenBearbeitungen() {
        return aufgabenBearbeitungen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<String, KlausurTeilnahme> getKlausurTeilnahmen() {
        return klausurTeilnahmen;
    }

    public Map<String, PraktikumTeilnahme> getPraktikumTeilnahme() {
        return praktikumTeilnahme;
    }

    public Map<String, SeminarTeilnahme> getSeminarTeilnahme() {
        return seminarTeilnahme;
    }
}
