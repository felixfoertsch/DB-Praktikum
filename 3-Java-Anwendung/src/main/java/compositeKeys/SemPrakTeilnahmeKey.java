package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SemPrakTeilnahmeKey implements Serializable {

    @Column(name = "studentId")
    Integer studentId;

    @Column(name = "klausurId")
    Integer klausurId;

    public SemPrakTeilnahmeKey() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemPrakTeilnahmeKey that = (SemPrakTeilnahmeKey) o;
        return studentId.equals(that.studentId) &&
                klausurId.equals(that.klausurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, klausurId);
    }
}
