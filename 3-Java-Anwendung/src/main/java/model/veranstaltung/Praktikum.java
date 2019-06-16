package model.veranstaltung;

import model.relationen.SemPrakTeilnahme;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "praktikum")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Praktikum extends Veranstaltung {

    @OneToMany
    private Collection<SemPrakTeilnahme> semPrakTeilnahmen;

    public Praktikum() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Collection<SemPrakTeilnahme> getSemPrakTeilnahmen() {
        return semPrakTeilnahmen;
    }

    public void setSemPrakTeilnahmen(Collection<SemPrakTeilnahme> semPrakTeilnahmen) {
        this.semPrakTeilnahmen = semPrakTeilnahmen;
    }
}
