package model;

public class AufgabenBearbeitung {
    private Aufgabe aufgabe;
    private Student student;
    private Double punkte;

    public AufgabenBearbeitung(Aufgabe aufgabe, Student student, String punkte) {
        this.aufgabe = aufgabe;
        this.student = student;
        this.punkte = Double.valueOf(punkte);
    }

    public String generateKey() {
        return student.getMatrikelNr() + "_" + aufgabe.generateKey();
    }
}
