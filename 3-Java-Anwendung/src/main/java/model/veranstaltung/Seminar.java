package model.veranstaltung;

import model.relationen.SemPrakTeilnahme;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "seminar")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
abstract public class Seminar extends Veranstaltung {

    @OneToMany(mappedBy = "veranstaltung")
    private List<SemPrakTeilnahme> semPrakTeilnahmen;

    protected Seminar() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */


}
