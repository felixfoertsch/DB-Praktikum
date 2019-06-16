package model.relationen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studentTeilnahmeKlausur")
public class KlausurTeilnahme {

    @EmbeddedId
    private KlausurTeilnahmeKey klausurTeilnahmeKey;
    @Column(name = "erschienen")
    private Boolean erschienen;
    @Column(name = "entschuldigt")
    private Boolean entschuldigt;
    @Column(name = "punkte")
    private Double punkte;
    @Column(name = "note")
    private Double note;

    public KlausurTeilnahme() {
    }

    @Embeddable
    class KlausurTeilnahmeKey implements Serializable {

        @Column(name = "studentId")
        private Integer studentId;

        @Column(name = "klausurId")
        private Integer klausurId;

        public KlausurTeilnahmeKey() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            KlausurTeilnahmeKey that = (KlausurTeilnahmeKey) o;
            return studentId.equals(that.studentId) && klausurId.equals(that.klausurId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, klausurId);
        }
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getStudentId() {
        return klausurTeilnahmeKey.studentId;
    }

    public void setStudentId(Integer studentId) { this.klausurTeilnahmeKey.studentId = studentId; }

    public Integer getKlausurId() {
        return klausurTeilnahmeKey.klausurId;
    }

    public void setKlausurId(Integer klausurId) { this.klausurTeilnahmeKey.klausurId = klausurId; }

    public Boolean getErschienen() {
        return erschienen;
    }

    public void setErschienen(Boolean erschienen) {
        this.erschienen = erschienen;
    }

    public Boolean getEntschuldigt() {
        return entschuldigt;
    }

    public void setEntschuldigt(Boolean entschuldigt) {
        this.entschuldigt = entschuldigt;
    }

    public Double getPunkte() {
        return punkte;
    }

    public void setPunkte(Double punkte) {
        this.punkte = punkte;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
