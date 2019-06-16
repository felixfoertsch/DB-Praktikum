package model.veranstaltung;

import model.veranstaltung.Grundvorlesung;
import model.veranstaltung.Veranstaltung;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "uebung")
public class Uebung extends Veranstaltung {

    @OneToOne
    private Grundvorlesung grundvorlesung;

    public Uebung() {
    }

}
