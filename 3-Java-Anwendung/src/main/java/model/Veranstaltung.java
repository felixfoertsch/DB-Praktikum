package model;

import java.time.Year;

public class Veranstaltung {
    private Integer id;
    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;
    private Mitarbeiter mitarbeiter;

    public void setData(String name, String jahr, String semester, String maxTeilnehmer, Mitarbeiter mitarbeiter) {
        this.name = name;
        this.jahr = Year.parse(jahr);
        this.semester = semester;
        this.maxTeilnehmer = Integer.valueOf(maxTeilnehmer);
        this.mitarbeiter = mitarbeiter;
    }
}
