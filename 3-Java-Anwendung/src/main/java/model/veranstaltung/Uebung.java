package model.veranstaltung;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "uebung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Uebung extends Veranstaltung {

//    @ManyToOne
//    private Grundvorlesung grundvorlesung;

    protected Uebung() {
    }



    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */


}
