package model;

import javax.persistence.Id;

public class Raum {

    @Id
    Integer id;
    Integer mitarbeiterId;
    String bezeichnung;

    public Raum(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
}
