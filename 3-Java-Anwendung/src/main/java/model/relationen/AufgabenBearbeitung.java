package model.relationen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bearbeitung")
public class AufgabenBearbeitung implements Serializable {

    @Id
    @Column(name = "studentId")
    private Integer studentId;

    @Id
    @Column(name = "aufgabeId")
    private Integer aufgabeId;

    @Column(name = "punkte")
    private Double punkte;

    public AufgabenBearbeitung() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AufgabenBearbeitung that = (AufgabenBearbeitung) o;
        return studentId.equals(that.studentId) && aufgabeId.equals(that.aufgabeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, aufgabeId);
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

    public Integer getAufgabeId() {
        return aufgabeId;
    }

    public void setAufgabeId(Integer aufgabeId) {
        this.aufgabeId = aufgabeId;
    }

    public Double getPunkte() {
        return punkte;
    }

    public void setPunkte(Double punkte) {
        this.punkte = punkte;
    }
}
