package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "spezialvorlesung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Spezialvorlesung extends Veranstaltung {

//    @OneToMany(mappedBy = "spezialvorlesung")
//    private List<Klausur> klausuren;

    protected Spezialvorlesung() {
    }



    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */


}
