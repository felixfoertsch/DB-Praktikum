package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "zwischenklausur")
@PrimaryKeyJoinColumn(name = "klausurId")
public class Zwischenklausur extends Klausur {

//    private Integer klausurId;

    public Zwischenklausur() {
    }

    @Override
    public String generateKey() {
        return this.getKlausurNr();
    }
}
