package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "spezialvorlesung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Spezialvorlesung extends Veranstaltung {

    @OneToMany
    private Collection<Klausur> klausuren;

    public Spezialvorlesung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Collection<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(Collection<Klausur> klausuren) {
        this.klausuren = klausuren;
    }
}
