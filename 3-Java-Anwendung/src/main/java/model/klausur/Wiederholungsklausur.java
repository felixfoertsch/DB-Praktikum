package model.klausur;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "wiederholungsklausur")
@PrimaryKeyJoinColumn(name = "klausur_id")
public class Wiederholungsklausur extends Abschlussklausur {
    public Wiederholungsklausur() {
    }
}
