package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VeranstaltungAbhaltungKey implements Serializable {

    @Column(name = "raumId")
    Integer raumId;

    @Column(name = "veranstaltungId")
    Integer veranstaltungId;

    public VeranstaltungAbhaltungKey() {
    }

    public Integer getRaumId() {
        return raumId;
    }

    public void setRaumId(Integer raumId) {
        this.raumId = raumId;
    }

    public Integer getVeranstaltungId() {
        return veranstaltungId;
    }

    public void setVeranstaltungId(Integer veranstaltungId) {
        this.veranstaltungId = veranstaltungId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeranstaltungAbhaltungKey that = (VeranstaltungAbhaltungKey) o;
        return raumId.equals(that.raumId) &&
                veranstaltungId.equals(that.veranstaltungId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(raumId, veranstaltungId);
    }
}
