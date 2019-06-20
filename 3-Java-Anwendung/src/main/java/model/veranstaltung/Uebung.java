package model.veranstaltung;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "uebung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Uebung extends Veranstaltung {

//    @OneToOne
//    private Grundvorlesung grundvorlesung;

    public Uebung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */


}
