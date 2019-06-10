package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "wiederholungsklausur")
@PrimaryKeyJoinColumn(name = "klausurId")
public class Wiederholungsklausur extends Abschlussklausur {

    public Wiederholungsklausur() {
    }
}
