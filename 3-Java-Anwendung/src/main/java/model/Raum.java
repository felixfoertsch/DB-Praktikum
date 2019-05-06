package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "raum")
public class Raum {

    @Id
    private Integer id;
    private String bezeichnung;

    private Map<String, Mitarbeiter> mitarbeiterMap;

    private Map<String, Klausur> klausurMap;

    private Map<String, VeranstaltungAbhaltung> abhaltungMap;

    public Raum(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Raum() {
    }
}
