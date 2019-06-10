package model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "aufgabe")
public class Aufgabe {

    @Id
    private Integer id;
    private Integer klausurId;
    private Integer rang;

    //Remove if not needed
    private String klausurNr;

    private Integer aufgabenNr;
    private Double maxPunkte;

    @ManyToOne
    @JoinColumn(name = "klausurId")
    private Klausur klausur;

    @OneToMany(mappedBy = "aufgabenBearbeitung")
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Aufgabe() {
    }

}
