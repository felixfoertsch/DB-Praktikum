package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "grundvorlesung")
public class Grundvorlesung extends Veranstaltung {

    @OneToMany
    private Map<String, Uebung> uebungen;
    @OneToMany
    private Map<String, Klausur> klausurMap;

    public Grundvorlesung() {
    }
}
