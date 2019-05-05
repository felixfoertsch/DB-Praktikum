package model;

import javax.persistence.Id;

public class Wiederholungsklausur extends Abschlussklausur {
    @Id
    private Integer klausurId;

    public Wiederholungsklausur() {
    }
}
