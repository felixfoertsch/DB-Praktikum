package model.relationen;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.time.Year;
import java.util.Objects;

@Entity
@Table(name = "studium")
public class Studium implements Serializable {

    @Id
    @Column(name = "studentId")
    private Integer studentId;

    @Id
    @Column(name = "studiengangId")
    private Integer studiengangId;

    @Column(name = "imma")
    private Year imma;

    @Column(name = "exma")
    private Year exma;

    @Column(name = "semester")
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
