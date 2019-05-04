package model;

import compositeKeys.KlausurTeilnahmeKey;

import javax.persistence.*;

@Entity
public class KlausurTeilnahme {

    @EmbeddedId
    private KlausurTeilnahmeKey klausurTeilnahmeKey;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Klausur klausur;

    private String typ;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Student student;
    private Boolean nichtErschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme(Klausur klausur, String typ, Student student, String nichtErschienen, String entschuldigt, String punkte, String note) {
        this.klausur = klausur;
        this.typ = typ;
        this.student = student;
        this.nichtErschienen = Boolean.valueOf(nichtErschienen);
        this.entschuldigt = Boolean.valueOf(entschuldigt);
        if (punkte.equals("NA")) {
            this.punkte = 0.0;
        } else {
            this.punkte = Double.valueOf(punkte);
        }
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public String generateKey() {
        return klausur.generateKey() + "_" + student.getMatrikelNr();
    }
}
