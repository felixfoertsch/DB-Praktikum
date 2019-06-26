package model.klausur;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "abschlussklausur")
@PrimaryKeyJoinColumn(name = "klausur_id")
public class Abschlussklausur extends Klausur {
    public Abschlussklausur() {
    }
}
