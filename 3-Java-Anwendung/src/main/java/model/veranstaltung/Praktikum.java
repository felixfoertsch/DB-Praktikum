package model.veranstaltung;

import model.relationen.SemPrakTeilnahme;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "praktikum")
public class Praktikum extends Veranstaltung {

    @OneToMany
    private Map<String, SemPrakTeilnahme> semPrakTeilnahmeMap;

    public Praktikum() {
    }

}
