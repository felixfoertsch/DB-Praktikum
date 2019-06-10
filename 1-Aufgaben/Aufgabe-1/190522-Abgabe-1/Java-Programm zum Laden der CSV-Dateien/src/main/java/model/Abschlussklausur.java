package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "abschlussklausur")
@PrimaryKeyJoinColumn(name = "klausurId")
public class Abschlussklausur extends Klausur {

//    private Integer klausurId;

    public Abschlussklausur() {
    }
}
