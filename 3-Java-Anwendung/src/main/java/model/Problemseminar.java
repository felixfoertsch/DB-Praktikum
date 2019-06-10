package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "problemseminar")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Problemseminar extends Seminar {

    public Problemseminar() {
    }
}
