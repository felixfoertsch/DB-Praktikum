package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "problemseminar")
@PrimaryKeyJoinColumn(name = "seminarId")
public class Problemseminar extends Seminar {

//    private Integer seminarId;

    public Problemseminar() {
    }
}
