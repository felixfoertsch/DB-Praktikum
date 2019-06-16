package model.relationen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bearbeitung")
public class AufgabenBearbeitung {

    @EmbeddedId
    private AufgabenBearbeitungKey aufgabenBearbeitungKey;
    @Column(name = "punkte")
    private Double punkte;

    public AufgabenBearbeitung() {
    }

    @Embeddable
    class AufgabenBearbeitungKey implements Serializable {

        @Column(name = "studentId")
        private Integer studentId;

        @Column(name = "aufgabeId")
        private Integer aufgabeId;

        public AufgabenBearbeitungKey() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            AufgabenBearbeitungKey that = (AufgabenBearbeitungKey) o;
            return studentId.equals(that.studentId) && aufgabeId.equals(that.aufgabeId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, aufgabeId);
        }
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getStudentId() {
        return aufgabenBearbeitungKey.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.aufgabenBearbeitungKey.studentId = studentId;
    }

    public Integer getAufgabeId() {
        return aufgabenBearbeitungKey.aufgabeId;
    }

    public void setAufgabeId(Integer aufgabeId) {
        this.aufgabenBearbeitungKey.aufgabeId = aufgabeId;
    }

    public Double getPunkte() {
        return punkte;
    }

    public void setPunkte(Double punkte) {
        this.punkte = punkte;
    }
}
