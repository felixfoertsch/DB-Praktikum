package model.relationen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "abhaltung")
public class VeranstaltungAbhaltung implements Serializable {

    @Id
    private Integer raumId;
    @Id
    private Integer veranstaltungId;
    private LocalTime zeit;
    private DayOfWeek tag;

    public VeranstaltungAbhaltung() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VeranstaltungAbhaltung that = (VeranstaltungAbhaltung) o;
        return raumId.equals(that.raumId) && veranstaltungId.equals(that.veranstaltungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raumId, veranstaltungId);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */
    public Integer getRaumId() {
        return raumId;
    }

    public void setRaumId(Integer raumId) {
        this.raumId = raumId;
    }

    public Integer getVeranstaltungId() {
        return veranstaltungId;
    }

    public void setVeranstaltungId(Integer veranstaltungId) {
        this.veranstaltungId = veranstaltungId;
    }

    public LocalTime getZeit() {
        return zeit;
    }

    public void setZeit(LocalTime zeit) {
        this.zeit = zeit;
    }

    public DayOfWeek getTag() {
        return tag;
    }

    public void setTag(DayOfWeek tag) {
        this.tag = tag;
    }
}

