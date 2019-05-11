package model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;

public class Veranstaltung {
    private Integer id;
    private String typ;
    private String name;
    private Integer jahr;
    private String semester;
    private Raum raum;
    private Integer maxTeilnehmer;
    private Collection<Mitarbeiter> dozenten;
    private LocalTime zeit;
    private DayOfWeek tag;
    private String kennung;

    public Veranstaltung() {
        this.dozenten = new ArrayList<>();
    }

    public void setData(
            String typ,
            String name,
            String jahr,
            String semester,
            String maxTeilnehmer,
            String zeit,
            String tag,
            String kennung) {
        this.typ = typ;
        this.name = name;
        this.jahr = Integer.valueOf(jahr);
        this.semester = semester;
        this.zeit = parseTime(zeit);
        this.tag = convertGerWeekDay(tag);
        this.maxTeilnehmer = Integer.valueOf(maxTeilnehmer);
        this.kennung = kennung;
    }

    public void setRaum(Raum raum) {
        this.raum = raum;
    }

    public void addDozent(Mitarbeiter mitarbeiter) {
        this.dozenten.add(mitarbeiter);
    }

    public String getKennung() {
        return kennung;
    }

    public String generateKey() {
        return kennung;
    }

    public String getName() {
        return name;
    }

    private LocalTime parseTime(String zeit) {
        if (zeit.equals("NULL")) {
            return null;
        }
        return LocalTime.parse(zeit);
    }
    private DayOfWeek convertGerWeekDay(String tag) {
        DayOfWeek day;
        switch (tag) {
            case "Montag"    : day = DayOfWeek.MONDAY; break;
            case "Dienstag"  : day = DayOfWeek.TUESDAY; break;
            case "Mittwoch"  : day = DayOfWeek.WEDNESDAY; break;
            case "Donnerstag": day = DayOfWeek.THURSDAY; break;
            case "Freitag"   : day = DayOfWeek.FRIDAY; break;
            case "Samstag"   : day = DayOfWeek.SATURDAY; break;
            case "Sonntag"   : day = DayOfWeek.SUNDAY; break;
            default: day = null;
        }
        return day;
    }

    public Integer getJahr() {
        return jahr;
    }

    public String getSemester() {
        return semester;
    }

    public Integer getMaxTeilnehmer() {
        return maxTeilnehmer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTyp() {
        return typ;
    }

    public Raum getRaum() {
        return raum;
    }

    public DayOfWeek getTag() {
        return tag;
    }

    public LocalTime getZeit() {
        return zeit;
    }
}
