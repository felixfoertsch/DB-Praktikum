package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Grundvorlesung extends Veranstaltung {

    @Id
    private Integer veranstaltungId;
    private Map<String, Uebung> uebungen;
    private Map<String, Klausur> klausurMap;

    public Grundvorlesung() {
        this.uebungen = new HashMap<>();
    }
}
