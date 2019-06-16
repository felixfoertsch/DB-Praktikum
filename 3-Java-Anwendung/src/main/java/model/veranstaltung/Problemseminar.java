package model.veranstaltung;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "problemseminar")
public class Problemseminar extends Seminar {

    public Problemseminar() {
    }
}
