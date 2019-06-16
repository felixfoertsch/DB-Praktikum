package dataimport.model;

public class PraktikumTeilnahme {
    private Praktikum praktikum;
    private Student student;
    private Double note;

    public PraktikumTeilnahme(Praktikum praktikum, Student student, String note) {
        this.praktikum = praktikum;
        this.student = student;
        if (note.equals("NA")) {
            this.note = 0.0;
        } else {
            this.note = Double.valueOf(note);
        }
    }

    public String generateKey() {
        return praktikum.generateKey() + "_" + student.getMatrikelNr();
    }

    public Praktikum getPraktikum() {
        return praktikum;
    }

    public Double getNote() {
        return note;
    }
}
