package model.veranstaltung;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "problemseminar")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Problemseminar extends Seminar {

    public Problemseminar() {
    }
}
