package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "abschlussklausur")
public class Abschlussklausur extends Klausur {

    private Integer klausurId;

    public Abschlussklausur() {
    }
}
