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
    private Year jahr;
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
            String name,
            String jahr,
            String semester,
            Raum raum,
            String maxTeilnehmer,
            String zeit,
            String tag,
            String kennung) {
        this.name = name;
        this.jahr = Year.parse(jahr);
        this.semester = semester;
        this.raum = raum;
        this.zeit = LocalTime.parse(zeit);
        this.tag = convertGerWeekDay(tag);
        this.maxTeilnehmer = Integer.valueOf(maxTeilnehmer);
        this.kennung = kennung;
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

    private DayOfWeek convertGerWeekDay(String tag) {
        DayOfWeek day;
        switch (tag) {
            case "Montag": day = DayOfWeek.MONDAY; break;
            case "Dienstag": day = DayOfWeek.TUESDAY; break;
            case "Mittwoch": day = DayOfWeek.WEDNESDAY; break;
            case "Donnerstag": day = DayOfWeek.THURSDAY; break;
            case "Freitag": day = DayOfWeek.FRIDAY; break;
            case "Samstag": day = DayOfWeek.SATURDAY; break;
            case "Sonntag": day = DayOfWeek.SUNDAY; break;
            default: day = null;
        }
        return day;
    }

}
