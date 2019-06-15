package model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "praktikum")
public class Praktikum extends Veranstaltung {

    @OneToMany
    private Map<String, SeminarTeilnahme> semPrakTeilnahmeMap;

    public Praktikum() {
    }

}
