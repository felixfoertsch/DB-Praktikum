package model;

public class Zwischenklausur extends Klausur {
    private Integer klausurId;

    public Zwischenklausur() {
    }

    @Override
    public String generateKey() {
        return this.getKlausurNr() + "_" + this.getTyp();
    }
}
