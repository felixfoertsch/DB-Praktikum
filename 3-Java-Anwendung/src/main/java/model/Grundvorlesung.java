package model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
