package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "veranstaltung")
public class Seminar extends Veranstaltung {

    private Integer veranstaltungId;
    private Map<String, SemPrakTeilnahme> semPrakTeilnahmeMap;

    public Seminar() {
        this.semPrakTeilnahmeMap = new HashMap<>();
    }

    public void addSemTeilnahme(SemPrakTeilnahme pt) {
        this.semPrakTeilnahmeMap.put(pt.generateSemKey(), pt);
    }
}
