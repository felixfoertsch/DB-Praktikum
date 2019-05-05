package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class VeranstaltungAbhaltung {

    private Integer id;

    private LocalTime zeit;
    private DayOfWeek tag;

    private Veranstaltung veranstaltung;
    private Raum raum;

    public VeranstaltungAbhaltung(LocalTime zeit, DayOfWeek tag, Raum raum) {
        this.zeit = zeit;
        this.tag = tag;
        this.raum = raum;
    }
}
