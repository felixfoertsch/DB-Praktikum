package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PraktikumTeilnahmeKey implements Serializable {

    @Column(name = "studentId")
    Long studentId;

    @Column(name = "klausurId")
    Long klausurId;

    public PraktikumTeilnahmeKey() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getKlausurId() {
        return klausurId;
    }

    public void setKlausurId(Long klausurId) {
        this.klausurId = klausurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PraktikumTeilnahmeKey that = (PraktikumTeilnahmeKey) o;
        return studentId.equals(that.studentId) &&
                klausurId.equals(that.klausurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, klausurId);
    }
}
