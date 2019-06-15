package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SemPrakTeilnahmeKey implements Serializable {

    @Column(name = "studentId")
    private Integer studentId;

    @Column(name = "veranstaltungId")
    private Integer veranstaltungId;

    public SemPrakTeilnahmeKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemPrakTeilnahmeKey that = (SemPrakTeilnahmeKey) o;
        return studentId.equals(that.studentId) &&
                veranstaltungId.equals(that.veranstaltungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, veranstaltungId);
    }
}
