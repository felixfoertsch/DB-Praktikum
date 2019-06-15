package model;

import compositeKeys.KlausurTeilnahmeKey;

import javax.persistence.*;

@Entity
@Table(name = "studentTeilnahmeKlausur")
public class KlausurTeilnahme {

    @EmbeddedId
    private KlausurTeilnahmeKey klausurTeilnahmeKey;

    private Boolean erschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme() {
    }

}
