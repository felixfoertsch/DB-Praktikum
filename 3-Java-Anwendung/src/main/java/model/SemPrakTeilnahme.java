package model;

public class SemPrakTeilnahme {
    private Seminar seminar;
    private Praktikum praktikum;
    private Student student;
    private Double note;

    public SemPrakTeilnahme(Praktikum praktikum, Student student, String note) {
        this.praktikum = praktikum;
        this.student = student;
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public SemPrakTeilnahme(Seminar seminar, Student student, String note) {
        this.seminar = seminar;
        this.student = student;
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public SemPrakTeilnahme() {
    }

    public String generatePrakKey() {
        return praktikum.generateKey() + "_" + student.getMatrikelNr();
    }

    public String generateSemKey() {
        return seminar.generateKey() + "_" + student.getMatrikelNr();
    }

}
