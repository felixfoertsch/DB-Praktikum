package importmodel;

import java.util.HashMap;
import java.util.Map;

public class Seminar extends Veranstaltung {
    Integer veranstaltungId;
    private Map<String, SeminarTeilnahme> seminarTeilnahme;

    public Seminar() {
        this.seminarTeilnahme = new HashMap<>();
    }

    public void addSeminarTeilnahme(SeminarTeilnahme st) {
        this.seminarTeilnahme.put(st.generateKey(), st);
    }

    public Map<String, SeminarTeilnahme> getSeminarTeilnahme() {
        return seminarTeilnahme;
    }
}
