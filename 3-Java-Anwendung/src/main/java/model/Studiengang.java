package model;

public class Studiengang {
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    public Studiengang(String name, String abschluss, String regelstudienzeit) {
        this.name = name;
        this.abschluss = abschluss;
        this.regelstudienzeit = Integer.valueOf(regelstudienzeit);
    }
}
