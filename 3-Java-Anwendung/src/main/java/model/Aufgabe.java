package model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "aufgabe")
public class Aufgabe {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer rang;
    private Double maxPunkte;
    @OneToOne
    private Klausur klausur;
    @Transient
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Aufgabe() {
    }

}
