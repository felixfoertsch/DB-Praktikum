package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Problemseminar extends Seminar {

    @Id
    private Integer seminarId;

    public Problemseminar() {
    }
}
