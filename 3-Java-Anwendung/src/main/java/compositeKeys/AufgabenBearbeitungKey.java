package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AufgabenBearbeitungKey implements Serializable {

    @Column(name = "studentId")
    Long studentId;

    @Column(name = "aufgabeId")
    Long aufgabeId;

    public AufgabenBearbeitungKey() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getAufgabeId() {
        return aufgabeId;
    }

    public void setAufgabeId(Long aufgabeId) {
        this.aufgabeId = aufgabeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AufgabenBearbeitungKey that = (AufgabenBearbeitungKey) o;
        return studentId.equals(that.studentId) &&
                aufgabeId.equals(that.aufgabeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, aufgabeId);
    }
}
