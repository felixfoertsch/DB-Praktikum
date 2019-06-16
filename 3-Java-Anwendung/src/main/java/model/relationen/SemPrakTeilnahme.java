package model.relationen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class SemPrakTeilnahme implements Serializable {

    @Id
    private Integer studentId;
    @Id
    private Integer veranstaltungId;
    private Double note;

    public SemPrakTeilnahme() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SemPrakTeilnahme that = (SemPrakTeilnahme) o;
        return studentId.equals(that.studentId) && veranstaltungId.equals(that.veranstaltungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, veranstaltungId);
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

    public Integer getVeranstaltungId() {
        return veranstaltungId;
    }

    public void setVeranstaltungId(Integer veranstaltungId) {
        this.veranstaltungId = veranstaltungId;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
