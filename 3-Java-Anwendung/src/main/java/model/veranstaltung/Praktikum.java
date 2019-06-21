package model.veranstaltung;

import model.relationen.SemPrakTeilnahme;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "praktikum")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Praktikum extends Veranstaltung {

    @OneToMany(mappedBy = "veranstaltung")
    private List<SemPrakTeilnahme> semPrakTeilnahmen;

    public Praktikum() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public List<SemPrakTeilnahme> getSemPrakTeilnahmen() {
        return semPrakTeilnahmen;
    }

    public void setSemPrakTeilnahmen(List<SemPrakTeilnahme> semPrakTeilnahmen) {
        this.semPrakTeilnahmen = semPrakTeilnahmen;
    }
}
