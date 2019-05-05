package model;

import javax.persistence.Id;

public class Problemseminar extends Seminar {
    @Id
    private Integer seminarId;

    public Problemseminar() {
    }
}
