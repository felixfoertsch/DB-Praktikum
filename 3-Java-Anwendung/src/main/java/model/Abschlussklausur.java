package model;

import javax.persistence.Id;

public class Abschlussklausur extends Klausur {
    @Id
    private Integer klausurId;

    public Abschlussklausur() {
    }
}
