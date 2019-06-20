package model.veranstaltung;

import model.klausur.Klausur;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "grundvorlesung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Grundvorlesung extends Veranstaltung {

//    @OneToMany
//    private List<Uebung> uebungen;
//    @OneToMany
//    private List<Klausur> klausuren;

    public Grundvorlesung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

}
