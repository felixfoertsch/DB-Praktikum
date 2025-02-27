package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "grundvorlesung")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Grundvorlesung extends Veranstaltung {

//    private Integer veranstaltungId;
    private Map<String, Uebung> uebungen;
    private Map<String, Klausur> klausurMap;

    public Grundvorlesung() {
        this.uebungen = new HashMap<>();
    }

    public Map<String, Uebung> getUebungen() {
        return uebungen;
    }
}
