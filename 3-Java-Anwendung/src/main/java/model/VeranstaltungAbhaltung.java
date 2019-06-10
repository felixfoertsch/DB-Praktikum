package model;

import compositeKeys.VeranstaltungAbhaltungKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Entity
@Table(name = "abhaltung")
public class VeranstaltungAbhaltung {

    @EmbeddedId
    private VeranstaltungAbhaltungKey veranstaltungAbhaltungKey;

    private LocalTime zeit;
    private DayOfWeek tag;

    private Veranstaltung veranstaltung;
    private Raum raum;

    public VeranstaltungAbhaltung(LocalTime zeit, DayOfWeek tag, Raum raum) {
        this.zeit = zeit;
        this.tag = tag;
        this.raum = raum;
    }

    public VeranstaltungAbhaltung() {
    }
}
