package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudiumKey implements Serializable {

    @Column(name = "studentId")
    private Integer studentId;

    @Column(name = "studiengangId")
    private Integer studiengangId;

    public StudiumKey() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudiumKey that = (StudiumKey) o;
        return studentId.equals(that.studentId) &&
                studiengangId.equals(that.studiengangId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, studiengangId);
    }
}
