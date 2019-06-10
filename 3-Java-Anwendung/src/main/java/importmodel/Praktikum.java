package importmodel;

import java.util.HashMap;
import java.util.Map;

public class Praktikum extends Veranstaltung {
    private Integer veranstaltungId;
    private Map<String, PraktikumTeilnahme> praktikumTeilnahme;

    public Praktikum() {
        this.praktikumTeilnahme = new HashMap<>();
    }

    public void addPraktikumTeilnahme(PraktikumTeilnahme pt) {
        this.praktikumTeilnahme.put(pt.generateKey(), pt);
    }

    public Map<String, PraktikumTeilnahme> getPraktikumTeilnahme() {
        return praktikumTeilnahme;
    }
}
