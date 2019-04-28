package model;

public class Student {
    Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;
    private Studium studium;

    public Student(String matrikelNr, String vorname, String nachname, String uniMail, Studium studium) {
        this.matrikelNr = matrikelNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.uniMail = uniMail;
        this.studium = studium;
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }
}
