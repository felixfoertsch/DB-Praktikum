package model;

import java.time.Year;
import java.util.ArrayList;

public class Veranstaltung {
    private Integer id;
    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;
    private ArrayList<Mitarbeiter> betreuer;
    private String kennung;

    public Veranstaltung() {
        this.betreuer = new ArrayList<>();
    }

    public void setData(String name, String jahr, String semester, String maxTeilnehmer, String kennung) {
        this.name = name;
        this.jahr = Year.parse(jahr);
        this.semester = semester;
        this.maxTeilnehmer = Integer.valueOf(maxTeilnehmer);
        this.kennung = kennung;
    }

    public void setBetreuer(Mitarbeiter mitarbeiter) {
        this.betreuer.add(mitarbeiter);
    }
}
