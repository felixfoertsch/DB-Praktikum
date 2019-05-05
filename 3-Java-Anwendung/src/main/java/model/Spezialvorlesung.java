package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Spezialvorlesung extends Veranstaltung {

    @Id
    private Integer veranstaltungId;
    private Map<String, Klausur> klausurMap;

    public Spezialvorlesung() {
    }
}
