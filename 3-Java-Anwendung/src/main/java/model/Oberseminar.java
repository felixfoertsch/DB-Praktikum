package model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "oberseminar")
public class Oberseminar extends Seminar {

    public Oberseminar() {
    }
}
