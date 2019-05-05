package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Abschlussklausur extends Klausur {

    @Id
    private Integer klausurId;

    public Abschlussklausur() {
    }
}
