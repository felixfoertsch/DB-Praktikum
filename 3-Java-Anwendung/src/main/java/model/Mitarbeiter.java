package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter {

    @Id
    private Integer id;

    private String vorname;
    private String nachname;
    private String email;

    private Raum raum;

    //?
    private String titel;

    private Map<String, Klausur> klausurMap;

    public Mitarbeiter() {
    }

}
