package model;

import java.util.ArrayList;

public class Universitaet {
    private ArrayList<Student> studenten;
    private ArrayList<Mitarbeiter> mitarbeiter;
    private ArrayList<Veranstaltung> veranstaltungen;
    private ArrayList<Klausur> klausuren;

    public Universitaet() {
        this.studenten = new ArrayList<>();
        this.mitarbeiter = new ArrayList<>();
        this.veranstaltungen = new ArrayList<>();
        this.klausuren = new ArrayList<>();
    }

    public ArrayList<Student> getStudenten() {
        return studenten;
    }

    public ArrayList<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public ArrayList<Veranstaltung> getVeranstaltungen() {
        return veranstaltungen;
    }

    public ArrayList<Klausur> getKlausuren() {
        return klausuren;
    }

}
