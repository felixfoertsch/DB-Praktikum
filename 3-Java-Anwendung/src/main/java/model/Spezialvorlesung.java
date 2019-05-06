package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "spezialvorlesung")
public class Spezialvorlesung extends Veranstaltung {

    private Integer veranstaltungId;
    private Map<String, Klausur> klausurMap;

    public Spezialvorlesung() {
    }
}
