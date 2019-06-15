package model;

import compositeKeys.AufgabenBearbeitungKey;

import javax.persistence.*;

@Entity
@Table(name = "bearbeitung")
public class AufgabenBearbeitung {

    @EmbeddedId
    private AufgabenBearbeitungKey aufgabenBearbeitungKey;

    private Double punkte;

    public AufgabenBearbeitung() {
    }

}
