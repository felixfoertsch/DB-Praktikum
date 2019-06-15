package model;

import compositeKeys.StudiumKey;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table(name = "studium")
public class Studium {

    @EmbeddedId
    private StudiumKey studiumKey;
    private Year imma;
    private Year exma;
    private Integer semester;

    public Studium() {
    }
}
