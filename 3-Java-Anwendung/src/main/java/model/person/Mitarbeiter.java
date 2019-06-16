package model.person;

import model.Raum;
import model.klausur.Klausur;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "mitarbeiter")
public class Mitarbeiter {

    @Id
    private Integer id;
    private String vorname;
    private String nachname;
    private String email;

    @OneToOne
    private Raum raum;
    @OneToMany
    private Map<Integer, Klausur> klausurMap;

    public Mitarbeiter() {
    }

}
