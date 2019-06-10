package model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "veranstaltung")
@PrimaryKeyJoinColumn(name = "veranstaltungId")
public class Seminar extends Veranstaltung {

    private Map<String, SemPrakTeilnahme> semPrakTeilnahmeMap;

    public Seminar() {
    }

}
