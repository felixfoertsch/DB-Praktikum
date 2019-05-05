package model;

import javax.persistence.Id;
import java.util.Map;

public class Spezialvorlesung extends Veranstaltung {
    @Id
    private Integer veranstaltungId;
    private Map<String, Klausur> klausurMap;

    public Spezialvorlesung() {
    }
}
