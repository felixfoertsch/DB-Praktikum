package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "uebung")
public class Uebung extends Veranstaltung {

    private Integer veranstaltungId;


    private Integer grundvorlesungId;

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
