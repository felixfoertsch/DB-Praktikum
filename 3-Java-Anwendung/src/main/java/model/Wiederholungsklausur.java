package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wiederholungsklausur extends Abschlussklausur {

    @Id
    private Integer klausurId;

    public Wiederholungsklausur() {
    }
}
