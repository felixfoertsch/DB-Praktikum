package model.veranstaltung;

import model.relationen.SemPrakTeilnahme;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "seminar")
abstract public class Seminar extends Veranstaltung {

    @OneToMany
    private Map<Integer, SemPrakTeilnahme> semPrakTeilnahmeMap;

    public Seminar() {
    }

}
