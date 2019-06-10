package model;

import compositeKeys.SemPrakTeilnahmeKey;

import javax.persistence.*;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class SemPrakTeilnahme {

    @EmbeddedId
    private SemPrakTeilnahmeKey semPrakTeilnahmeKey;

    private Seminar seminar;
    private Praktikum praktikum;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "id")
    private Student student;
    private Double note;


    public SemPrakTeilnahme() {
    }

}
