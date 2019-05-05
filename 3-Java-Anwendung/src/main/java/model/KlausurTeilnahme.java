package model;

public class KlausurTeilnahme {
    private Klausur klausur;
    private String typ;
    private Student student;
    private Boolean erschienen;
    private Boolean entschuldigt;
    private Double punkte;
    private Double note;

    public KlausurTeilnahme(Klausur klausur, String typ, Student student, String nichtErschienen, String entschuldigt, String punkte, String note) {
        this.klausur = klausur;
        this.typ = typ;
        this.student = student;
        this.erschienen = !Boolean.valueOf(nichtErschienen);
        this.entschuldigt = Boolean.valueOf(entschuldigt);
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
}
