package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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
