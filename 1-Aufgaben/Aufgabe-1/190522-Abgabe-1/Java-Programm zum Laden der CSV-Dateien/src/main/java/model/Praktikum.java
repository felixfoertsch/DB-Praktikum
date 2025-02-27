package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "praktikum")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Praktikum extends Veranstaltung {

    //private Integer veranstaltungId;
    private Map<String, SemPrakTeilnahme> semPrakTeilnahmeMap;

    public Praktikum() {
        this.semPrakTeilnahmeMap = new HashMap<>();
    }

    public void addPrakTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahmeMap.put(pt.generatePrakKey(), pt);
    }

    public Map<String, PraktikumTeilnahme> getPraktikumTeilnahme() {
        return praktikumTeilnahme;
    }
}
