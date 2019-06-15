package model;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@MappedSuperclass
@Table(name = "veranstaltung")
abstract public class Seminar extends Veranstaltung {

    @OneToMany
    private Map<String, SeminarTeilnahme> semPrakTeilnahmeMap;

    public Seminar() {
    }

}
