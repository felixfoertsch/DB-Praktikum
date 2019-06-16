package model.relationen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studentTeilnahmeKlausur")
public class KlausurTeilnahme implements Serializable {

    @Id
    private Integer studentId;
    @Id
    private Integer klausurId;
    private Boolean erschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        KlausurTeilnahme that = (KlausurTeilnahme) o;
        return studentId.equals(that.studentId) && klausurId.equals(that.klausurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, klausurId);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getKlausurId() {
        return klausurId;
    }

    public void setKlausurId(Integer klausurId) {
        this.klausurId = klausurId;
    }

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
