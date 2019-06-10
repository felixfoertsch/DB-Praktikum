package model;

import compositeKeys.KlausurTeilnahmeKey;

import javax.persistence.*;

@Entity
@Table(name = "studentTeilnahmeKlausur")
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
    private Boolean erschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme() {
    }

}
