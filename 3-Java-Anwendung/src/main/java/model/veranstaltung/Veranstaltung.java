package model.veranstaltung;

import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "veranstaltung")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Veranstaltung implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer jahr;
    private String semester;
    private Integer maxTeilnehmer;

    @OneToMany(mappedBy = "veranstaltung")
    private List<VeranstaltungAbhaltung> abhaltungen;

    @ManyToMany(mappedBy = "veranstaltungen")
    private List<Mitarbeiter> betreuer;

    protected Veranstaltung() {
    }

    public Veranstaltung(String name, Integer jahr, String semester, Integer maxTeilnehmer) {
        this.name = name;
        this.jahr = jahr;
        this.semester = semester;
        this.maxTeilnehmer = maxTeilnehmer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Veranstaltung veranstaltung = (Veranstaltung) o;
        return Objects.equals(this.id, veranstaltung.id);
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

    public Integer getJahr() {
        return jahr;
    }

    public void setJahr(Integer jahr) {
        this.jahr = jahr;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Integer getMaxTeilnehmer() {
        return maxTeilnehmer;
    }

    public void setMaxTeilnehmer(Integer maxTeilnehmer) {
        this.maxTeilnehmer = maxTeilnehmer;
    }

    public List<VeranstaltungAbhaltung> getAbhaltungen() {
        return abhaltungen;
    }

    public void setAbhaltungen(List<VeranstaltungAbhaltung> abhaltungen) {
        this.abhaltungen = abhaltungen;
    }

    public List<Mitarbeiter> getBetreuer() {
        return betreuer;
    }

    public void setBetreuer(List<Mitarbeiter> betreuer) {
        this.betreuer = betreuer;
    }
}
