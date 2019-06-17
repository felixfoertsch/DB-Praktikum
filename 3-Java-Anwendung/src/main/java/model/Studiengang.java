package model;

import model.relationen.Studium;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "studiengang")
public class Studiengang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    @OneToMany
    private Collection<Studium> studien;

    public Studiengang() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbschluss() {
        return abschluss;
    }

    public void setAbschluss(String abschluss) {
        this.abschluss = abschluss;
    }

    public Integer getRegelstudienzeit() {
        return regelstudienzeit;
    }

    public void setRegelstudienzeit(Integer regelstudienzeit) {
        this.regelstudienzeit = regelstudienzeit;
    }

    public Collection<Studium> getStudien() {
        return studien;
    }

    public void setStudien(Collection<Studium> studien) {
        this.studien = studien;
    }
}
