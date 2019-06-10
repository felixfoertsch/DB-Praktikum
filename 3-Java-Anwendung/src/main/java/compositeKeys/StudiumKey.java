package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudiumKey implements Serializable {

    @Column(name = "studentId")
    Integer studentId;

    @Column(name = "studiengangId")
    Integer studiengangId;

    public StudiumKey() {
    }

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
