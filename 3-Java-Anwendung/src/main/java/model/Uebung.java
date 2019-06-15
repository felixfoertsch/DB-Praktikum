package model;

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
