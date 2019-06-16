package model;

import model.relationen.Studium;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "studiengang")
public class Studiengang {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    @OneToMany
    private Collection<Studium> studien;

    public Studiengang() {
    }

}
