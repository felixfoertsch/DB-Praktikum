package model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "studiengang")
public class Studiengang {

    @Id
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    @OneToMany
    private Map<String, Studium> studium;

    public Studiengang() {
    }

}
