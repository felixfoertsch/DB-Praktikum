package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Map;

@Entity
@Table(name = "spezialvorlesung")
public class Spezialvorlesung extends Veranstaltung {

    @Transient
    private Map<String, Klausur> klausurMap;

    public Spezialvorlesung() {
    }
}
