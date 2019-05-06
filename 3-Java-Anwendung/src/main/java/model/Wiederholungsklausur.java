package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wiederholungsklausur")
public class Wiederholungsklausur extends Abschlussklausur {

    private Integer klausurId;

    public Wiederholungsklausur() {
    }
}
