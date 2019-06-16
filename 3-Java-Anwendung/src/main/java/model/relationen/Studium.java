package model.relationen;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "studium")
public class Studium implements Serializable {

    @Id
    private Integer studentId;
    @Id
    private Integer studiengangId;
    private Year imma;
    private Year exma;
    private Integer semester;

    public Studium() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Studium that = (Studium) o;
        return studentId.equals(that.studentId) && studiengangId.equals(that.studiengangId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studiengangId);
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

    public Integer getStudiengangId() {
        return studiengangId;
    }

    public void setStudiengangId(Integer studiengangId) {
        this.studiengangId = studiengangId;
    }

    public Year getImma() {
        return imma;
    }

    public void setImma(Year imma) {
        this.imma = imma;
    }

    public Year getExma() {
        return exma;
    }

    public void setExma(Year exma) {
        this.exma = exma;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }
}
