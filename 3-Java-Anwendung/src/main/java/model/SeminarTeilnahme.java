package model;

import compositeKeys.SemPrakTeilnahmeKey;

import javax.persistence.*;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class SeminarTeilnahme {

    @EmbeddedId
    private SemPrakTeilnahmeKey semPrakTeilnahmeKey;

    private Double note;


    public SeminarTeilnahme() {
    }

}
