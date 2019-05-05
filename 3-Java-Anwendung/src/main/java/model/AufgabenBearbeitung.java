package model;

import compositeKeys.AufgabenBearbeitungKey;

import javax.persistence.*;

@Entity
public class AufgabenBearbeitung {

    @EmbeddedId
    private AufgabenBearbeitungKey aufgabenBearbeitungKey;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Aufgabe aufgabe;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Student student;

    private Double punkte;

    public AufgabenBearbeitung(Aufgabe aufgabe, Student student, String punkte) {
        this.aufgabe = aufgabe;
        this.student = student;
        this.punkte = Double.valueOf(punkte);
    }

    public AufgabenBearbeitung() {
    }

    public String generateKey() {
        return student.getMatrikelNr() + "_" + aufgabe.generateKey();
    }
}
