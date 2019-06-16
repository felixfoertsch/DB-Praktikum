package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "grundvorlesung")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Grundvorlesung extends Veranstaltung {

    @OneToMany
    private Collection<Uebung> uebungen;
    @OneToMany
    private Collection<Klausur> klausuren;

    public Grundvorlesung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Collection<Uebung> getUebungen() {
        return uebungen;
    }

    public void setUebungen(Collection<Uebung> uebungen) {
        this.uebungen = uebungen;
    }

    public Collection<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(Collection<Klausur> klausuren) {
        this.klausuren = klausuren;
    }
}
