package model;

import javax.persistence.Id;

public class Oberseminar extends Seminar {
    @Id
    private Integer seminarId;

    public Oberseminar() {
    }
}
