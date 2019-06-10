package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "uebung")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Uebung extends Veranstaltung {

//    private Integer veranstaltungId;


    private Grundvorlesung grundvorlesung;

    public Uebung() {
    }

}
