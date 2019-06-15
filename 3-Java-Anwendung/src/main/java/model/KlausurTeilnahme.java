package model;

import compositeKeys.KlausurTeilnahmeKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

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
