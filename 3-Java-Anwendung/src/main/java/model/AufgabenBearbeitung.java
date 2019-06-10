package model;

import compositeKeys.AufgabenBearbeitungKey;

import javax.persistence.*;

@Entity
@Table(name = "bearbeitung")
public class AufgabenBearbeitung {

    @EmbeddedId
    private AufgabenBearbeitungKey aufgabenBearbeitungKey;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "aufgabeId")
    private Aufgabe aufgabe;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "studentId")
    private Student student;

    private Double punkte;


    public AufgabenBearbeitung() {
    }

}
