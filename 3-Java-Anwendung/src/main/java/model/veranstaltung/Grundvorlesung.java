package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "grundvorlesung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Grundvorlesung extends Veranstaltung {

    @OneToMany(mappedBy = "grundvorlesung")
    private List<Uebung> uebungen;

    @OneToMany(mappedBy = "grundvorlesung")
    private List<Klausur> klausuren;

    public Grundvorlesung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public List<Uebung> getUebungen() {
        return uebungen;
    }

    public void setUebungen(List<Uebung> uebungen) {
        this.uebungen = uebungen;
    }

    public List<Klausur> getKlausuren() {
        return klausuren;
    }

    public void setKlausuren(List<Klausur> klausuren) {
        this.klausuren = klausuren;
    }
}
