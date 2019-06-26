package model;

public class SeminarTeilnahme {
    private Seminar seminar;
    private Student student;
    private Double note;

    public SeminarTeilnahme(Seminar seminar, Student student, String note) {
        this.seminar = seminar;
        this.student = student;
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public String generateKey() {
        return seminar.generateKey() + "_" + student.getMatrikelNr();
    }

    public Seminar getSeminar() {
        return seminar;
    }

    public Double getNote() {
        return note;
    }
}
