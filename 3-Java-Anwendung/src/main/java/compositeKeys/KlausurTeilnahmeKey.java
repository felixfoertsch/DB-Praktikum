package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KlausurTeilnahmeKey implements Serializable {

    @Column(name = "studentId")
    private Integer studentId;

    @Column(name = "klausurId")
    private Integer klausurId;

    public KlausurTeilnahmeKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KlausurTeilnahmeKey that = (KlausurTeilnahmeKey) o;
        return studentId.equals(that.studentId) &&
                klausurId.equals(that.klausurId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, klausurId);
    }
}
