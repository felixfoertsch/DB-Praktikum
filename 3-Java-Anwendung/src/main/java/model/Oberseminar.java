package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Oberseminar extends Seminar {

    @Id
    private Integer seminarId;

    public Oberseminar() {
    }
}
