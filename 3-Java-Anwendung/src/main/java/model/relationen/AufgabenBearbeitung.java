package model.relationen;

import model.compositeKeys.AufgabenBearbeitungKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bearbeitung")
public class AufgabenBearbeitung {

    @EmbeddedId
    private AufgabenBearbeitungKey aufgabenBearbeitungKey;

    private Double punkte;

    public AufgabenBearbeitung() {
    }

}
