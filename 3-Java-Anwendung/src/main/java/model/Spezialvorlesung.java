package model;

import javax.persistence.Entity;
import java.util.Map;

@Entity
public class Spezialvorlesung extends Veranstaltung {

    private Integer veranstaltungId;
    private Map<String, Klausur> klausurMap;

    public Spezialvorlesung() {
    }
}
