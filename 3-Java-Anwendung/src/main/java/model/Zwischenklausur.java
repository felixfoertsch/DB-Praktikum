package model;

import javax.persistence.Entity;

@Entity
public class Zwischenklausur extends Klausur {

    private Integer klausurId;

    public Zwischenklausur() {
    }

    @Override
    public String generateKey() {
        return this.getKlausurNr();
    }
}
