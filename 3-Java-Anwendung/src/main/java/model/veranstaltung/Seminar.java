package model.veranstaltung;

import model.relationen.SeminarTeilnahme;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "seminar")
abstract public class Seminar extends Veranstaltung {

    @OneToMany
    private Map<String, SeminarTeilnahme> semPrakTeilnahmeMap;

    public Seminar() {
    }

}
