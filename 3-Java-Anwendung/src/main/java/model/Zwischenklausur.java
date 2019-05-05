package model;

import javax.persistence.Id;

public class Zwischenklausur extends Klausur {
    @Id
    private Integer klausurId;

    public Zwischenklausur() {
    }

    @Override
    public String generateKey() {
        return this.getKlausurNr();
    }
}
