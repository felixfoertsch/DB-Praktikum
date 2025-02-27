package model.relationen;

import model.Studiengang;
import model.person.Student;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studium")
public class Studium implements Serializable {

    @Id
    @ManyToOne
    private Student student;
    @Id
    @ManyToOne
    private Studiengang studiengang;

    private Integer imma;
    private Integer exma;
    private Integer semester;

    public Studium() {
    }

    public Studium(Student student, Studiengang studiengang, Integer imma, Integer exma, Integer semester) {
        this.student = student;
        this.studiengang = studiengang;
        this.imma = imma;
        this.exma = exma;
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Studium studium = (Studium) o;
        return Objects.equals(this.student, studium.getStudent()) &&
                Objects.equals(this.studiengang, studium.getStudiengang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.studiengang);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Studiengang getStudiengang() {
        return studiengang;
    }

    public void setStudiengang(Studiengang studiengang) {
        this.studiengang = studiengang;
    }

    public Integer getImma() {
        return imma;
    }

    public void setImma(Integer imma) {
        this.imma = imma;
    }

    public Integer getExma() {
        return exma;
    }

    public void setExma(Integer exma) {
        this.exma = exma;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

}
