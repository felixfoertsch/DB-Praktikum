package model;

import compositeKeys.StudiumKey;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Year;

@Entity
@Table(name = "studium")
public class Studium {

    @EmbeddedId
    private StudiumKey studiumKey;

    private Student student;
    private Studiengang studiengang;
    private Year imma;
    private Year exma;
    private Integer semester;

    public Studium() {
    }
}
