package model;

import model.klausur.Klausur;
import model.relationen.AufgabenBearbeitung;

import javax.persistence.*;
import java.util.Collection;

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
    private Collection<AufgabenBearbeitung> aufgabenBearbeitungen;

    public Aufgabe() {
    }

}
