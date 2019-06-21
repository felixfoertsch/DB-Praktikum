package model.veranstaltung;

import javax.persistence.*;

@Entity
@Table(name = "uebung")
@PrimaryKeyJoinColumn(name = "veranstaltung_id")
public class Uebung extends Veranstaltung {

    @ManyToOne
    @JoinColumn(
            name = "grundvorlesung_id",
            foreignKey = @ForeignKey(name = "veranstaltung_id")
    )
    private Grundvorlesung grundvorlesung;

    public Uebung() {
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Grundvorlesung getGrundvorlesung() {
        return grundvorlesung;
    }

    public void setGrundvorlesung(Grundvorlesung grundvorlesung) {
        this.grundvorlesung = grundvorlesung;
    }
}
