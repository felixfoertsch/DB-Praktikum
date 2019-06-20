package model;

import model.relationen.Studium;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "studiengang")
public class Studiengang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    @OneToMany(mappedBy = "studiengang")
    private List<Studium> studien;

    protected Studiengang() {
    }

    public Studiengang(String name, String abschluss, Integer regelstudienzeit) {
        this.name = name;
        this.abschluss = abschluss;
        this.regelstudienzeit = regelstudienzeit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Studiengang studiengang = (Studiengang) o;
        return Objects.equals(this.id, studiengang.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
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

    public List<Studium> getStudien() {
        return studien;
    }

    public void setStudien(List<Studium> studien) {
        this.studien = studien;
    }
}
