package model;

public class Zwischenklausur extends Klausur {
    Integer klausurId;

    @Override
    public String generateKey() {
        return this.getKlausurNr();// + "_" + this.getTyp();
    }
}
