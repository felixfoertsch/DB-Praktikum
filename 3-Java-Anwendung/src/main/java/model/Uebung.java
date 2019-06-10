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

    @Override
    public String generateKey() {
        return super.getKennung() + "_" + extractGroupID();
    }

    private String extractGroupID() {
        StringBuilder name = new StringBuilder(super.getName()).reverse();
        return Character.toString(name.charAt(0)).toLowerCase();
    }
}
