package compositeKeys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VeranstaltungAbhaltungKey implements Serializable {

    @Column(name = "raumId")
    private Integer raumId;

    @Column(name = "veranstaltungId")
    private Integer veranstaltungId;

    public VeranstaltungAbhaltungKey() {
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
