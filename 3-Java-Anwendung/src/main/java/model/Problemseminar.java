package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "problemseminar")
public class Problemseminar extends Seminar {

    private Integer seminarId;

    public Problemseminar() {
    }
}
