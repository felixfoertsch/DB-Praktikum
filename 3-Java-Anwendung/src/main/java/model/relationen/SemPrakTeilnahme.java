package model.relationen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "studentTeilnahmeVeranstaltung")
public class SemPrakTeilnahme {

    @EmbeddedId
    private SemPrakTeilnahmeKey semPrakTeilnahmeKey;
    @Column(name = "note")
    private Double note;

    public SemPrakTeilnahme() {
    }


    @Embeddable
    class SemPrakTeilnahmeKey implements Serializable {

        @Column(name = "studentId")
        private Integer studentId;

        @Column(name = "veranstaltungId")
        private Integer veranstaltungId;

        public SemPrakTeilnahmeKey() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            SemPrakTeilnahmeKey that = (SemPrakTeilnahmeKey) o;
            return studentId.equals(that.studentId) && veranstaltungId.equals(that.veranstaltungId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, veranstaltungId);
        }
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getStudentI() {
        return semPrakTeilnahmeKey.studentId;
    }

    public Integer getVeranstaltungId() {
        return semPrakTeilnahmeKey.veranstaltungId;
    }

    public void setStudentI(Integer studentId) {
        semPrakTeilnahmeKey.studentId = studentId;
    }

    public void setVeranstaltungId(Integer veranstaltungId) {
        semPrakTeilnahmeKey.veranstaltungId = veranstaltungId;
    }

    public SemPrakTeilnahmeKey getSemPrakTeilnahmeKey() {
        return semPrakTeilnahmeKey;
    }

    public void setSemPrakTeilnahmeKey(SemPrakTeilnahmeKey semPrakTeilnahmeKey) {
        this.semPrakTeilnahmeKey = semPrakTeilnahmeKey;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }
}
