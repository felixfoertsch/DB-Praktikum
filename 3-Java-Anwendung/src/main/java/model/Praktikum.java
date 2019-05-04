package model;

import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

public class Praktikum extends Veranstaltung {

    @Id
    private Integer veranstaltungId;
    private Map<String, PraktikumTeilnahme> praktikumTeilnahme;

    public Praktikum() {
        this.praktikumTeilnahme = new HashMap<>();
    }

    public void addPraktikumTeilnahme(PraktikumTeilnahme pt) {
        this.praktikumTeilnahme.put(pt.generateKey(), pt);
    }
}
