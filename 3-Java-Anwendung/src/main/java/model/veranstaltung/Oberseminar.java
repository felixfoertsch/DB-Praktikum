package model.veranstaltung;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "oberseminar")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Oberseminar extends Seminar {

    public Oberseminar() {
    }
}
