package model.klausur;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "zwischenklausur")
@PrimaryKeyJoinColumn(name = "klausur_id")
public class Zwischenklausur extends Klausur {
    public Zwischenklausur() {
    }
}
