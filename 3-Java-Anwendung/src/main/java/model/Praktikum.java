package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Praktikum extends Veranstaltung {

    @Id
    private Integer veranstaltungId;
    private Map<String, SemPrakTeilnahme> semPrakTeilnahmeMap;

    public Praktikum() {
        this.semPrakTeilnahmeMap = new HashMap<>();
    }

    public void addPrakTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahmeMap.put(pt.generatePrakKey(), pt);
    }
}
