package model.relationen;

import model.Raum;
import model.veranstaltung.Veranstaltung;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "abhaltung")
public class VeranstaltungAbhaltung implements Serializable {

    @Id
    @ManyToOne
    private Raum raum;
    @Id
    @ManyToOne
    private Veranstaltung veranstaltung;

    private LocalTime zeit;
    private String wochentag;

    protected VeranstaltungAbhaltung() {
    }

    public VeranstaltungAbhaltung(Raum raum, Veranstaltung veranstaltung, LocalTime zeit, String wochentag) {
        this.raum = raum;
        this.veranstaltung = veranstaltung;
        this.zeit = zeit;
        this.wochentag = wochentag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VeranstaltungAbhaltung veranstaltungAbhaltung = (VeranstaltungAbhaltung) o;
        return Objects.equals(this.raum, veranstaltungAbhaltung.getRaum()) &&
                Objects.equals(this.veranstaltung, veranstaltungAbhaltung.getVeranstaltung());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.raum, this.veranstaltung);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public LocalTime getZeit() {
        return zeit;
    }

    public void setZeit(LocalTime zeit) {
        this.zeit = zeit;
    }

    public String getWochentag() {
        return wochentag;
    }

    public void setWochentag(String wochentag) {
        this.wochentag = wochentag;
    }

    public Raum getRaum() {
        return raum;
    }

    public void setRaum(Raum raum) {
        this.raum = raum;
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }
}

