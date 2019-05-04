package model;

import compositeKeys.PraktikumTeilnahmeKey;

import javax.persistence.*;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class PraktikumTeilnahme {

    @EmbeddedId
    PraktikumTeilnahmeKey praktikumTeilnahmeKey;

    private Praktikum praktikum;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Student student;
    private Double note;

    public PraktikumTeilnahme(Praktikum praktikum, Student student, String note) {
        this.praktikum = praktikum;
        this.student = student;
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public String generateKey() {
        return praktikum.generateKey() + "_" + student.getMatrikelNr();
    }
}
