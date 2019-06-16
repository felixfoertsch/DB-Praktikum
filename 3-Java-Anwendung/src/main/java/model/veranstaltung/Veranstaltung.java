package model.veranstaltung;

import model.person.Mitarbeiter;
import model.relationen.VeranstaltungAbhaltung;

import javax.persistence.*;
import java.time.Year;
import java.util.Collection;

@Entity
@Table(name = "veranstaltung")
@Inheritance(strategy = InheritanceType.JOINED)
abstract public class Veranstaltung {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Year jahr;
    private String semester;
    private Integer maxTeilnehmer;

    @OneToMany
    private Collection<VeranstaltungAbhaltung> abhaltungen;
    @OneToMany
    private Collection<Mitarbeiter> betreuer;

    public Veranstaltung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getJahr() {
        return jahr;
    }

    public void setJahr(Year jahr) {
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

    public Collection<VeranstaltungAbhaltung> getAbhaltungen() {
        return abhaltungen;
    }

    public void setAbhaltungen(Collection<VeranstaltungAbhaltung> abhaltungen) {
        this.abhaltungen = abhaltungen;
    }

    public Collection<Mitarbeiter> getBetreuer() {
        return betreuer;
    }

    public void setBetreuer(Collection<Mitarbeiter> betreuer) {
        this.betreuer = betreuer;
    }
}
