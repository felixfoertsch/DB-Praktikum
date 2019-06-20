package model.veranstaltung;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oberseminar")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Oberseminar extends Seminar {

    public Oberseminar() {
    }
}
