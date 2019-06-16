package model.relationen;

import model.compositeKeys.SemPrakTeilnahmeKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class PraktikumTeilnahme {

    @EmbeddedId
    private SemPrakTeilnahmeKey semPrakTeilnahmeKey;

    private Double note;

    public PraktikumTeilnahme() {
    }

}
