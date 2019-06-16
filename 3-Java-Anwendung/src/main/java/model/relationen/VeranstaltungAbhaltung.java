package model.relationen;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "abhaltung")
public class VeranstaltungAbhaltung {

    @EmbeddedId
    private VeranstaltungAbhaltungKey veranstaltungAbhaltungKey;

    private LocalTime zeit;
    private DayOfWeek tag;

    public VeranstaltungAbhaltung() {
    }

    @Embeddable
    class VeranstaltungAbhaltungKey implements Serializable {

        @Column(name = "raumId")
        private Integer raumId;

        @Column(name = "veranstaltungId")
        private Integer veranstaltungId;

        public VeranstaltungAbhaltungKey() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            VeranstaltungAbhaltungKey that = (VeranstaltungAbhaltungKey) o;
            return raumId.equals(that.raumId) && veranstaltungId.equals(that.veranstaltungId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(raumId, veranstaltungId);
        }
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getRaumId() {
        return veranstaltungAbhaltungKey.raumId;
    }

    public void setRaumId(Integer raumId) { this.veranstaltungAbhaltungKey.raumId = raumId; }

    public Integer getVeranstaltungId() {
        return veranstaltungAbhaltungKey.veranstaltungId;
    }

    public void setVeranstaltungId(Integer veranstaltungId) { this.veranstaltungAbhaltungKey.veranstaltungId = veranstaltungId; }

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

