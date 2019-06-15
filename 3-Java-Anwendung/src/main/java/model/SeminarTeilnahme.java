package model;

import compositeKeys.SemPrakTeilnahmeKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class SeminarTeilnahme {

    @EmbeddedId
    private SemPrakTeilnahmeKey semPrakTeilnahmeKey;

    private Double note;


    public SeminarTeilnahme() {
    }

}
