package model.veranstaltung;

import model.relationen.SemPrakTeilnahme;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "seminar")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
abstract public class Seminar extends Veranstaltung {

    @OneToMany
    private Collection<SemPrakTeilnahme> semPrakTeilnahmen;

    public Seminar() {
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
