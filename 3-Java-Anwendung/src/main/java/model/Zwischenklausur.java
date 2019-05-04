package model;

import javax.persistence.Id;

public class Zwischenklausur extends Klausur {

    @Id
    Integer klausurId;

    @Override
    public String generateKey() {
        return this.getKlausurNr();
    }
}
