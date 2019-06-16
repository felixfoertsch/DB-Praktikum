package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "spezialvorlesung")
public class Spezialvorlesung extends Veranstaltung {

    @OneToMany
    private Map<String, Klausur> klausurMap;

    public Spezialvorlesung() {
    }
}
