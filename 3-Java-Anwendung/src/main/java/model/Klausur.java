package model;

import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Klausur {
    Integer id;
    Integer spezialvorlesungId;
    Integer grundvorlesungId;
    LocalDate datum;
    LocalTime uhrzeitVon;
    Integer gesamtpunktzahl;
    ArrayList<Mitarbeiter> aufsichten;

    public Klausur() {
        aufsichten = new ArrayList<>();
    }

    public void setDatum(String datum) {
        this.datum = LocalDate.parse(datum);
    }

    public void setUhrzeitVon(String uhrzeitVon) {
        this.uhrzeitVon = LocalTime.parse(uhrzeitVon);
    }

    public void setGesamtpunktzahl(String gesamtpunktzahl) {
        this.gesamtpunktzahl = Integer.valueOf(gesamtpunktzahl);
    }

    public void addAufsicht(Mitarbeiter mitarbeiter) {
        this.aufsichten.add(mitarbeiter);
    }
}
