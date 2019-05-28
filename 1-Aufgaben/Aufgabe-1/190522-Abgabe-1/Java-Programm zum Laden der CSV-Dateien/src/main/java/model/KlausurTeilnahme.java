package model;

public class KlausurTeilnahme {
    private Klausur klausur;
    private String typ;
    private Student student;
    private Boolean nichtErschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme(Klausur klausur, String typ, Student student, String nichtErschienen, String entschuldigt, String punkte, String note) {
        this.klausur = klausur;
        this.typ = typ;
        this.student = student;
        this.nichtErschienen = stringToBool(nichtErschienen);
        this.entschuldigt = stringToBool(entschuldigt);
        if (punkte.equals("NA")) {
            this.punkte = 0.0;
        } else {
            this.punkte = Double.valueOf(punkte);
        }
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public String generateKey() {
        return klausur.generateKey() + "_" + student.getMatrikelNr();
    }

    public Klausur getKlausur() {
        return klausur;
    }

    public Boolean getErschienen() {
        return !nichtErschienen;
    }

    public Boolean getEntschuldigt() {
        return entschuldigt;
    }

    public Double getPunkte() {
        return punkte;
    }

    public Double getNote() {
        return note;
    }

    private Boolean stringToBool(String string) {
        if (string.equals("1")) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
