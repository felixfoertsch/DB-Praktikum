package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studiengang")
public class Studiengang {

    @Id
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    private Studium studium;

    public Studiengang(String name, String abschluss, String regelstudienzeit) {
        this.name = name;
        this.abschluss = abschluss;
        this.regelstudienzeit = Integer.valueOf(regelstudienzeit);
    }

    public Studiengang() {
    }

    public String getName() {
        return name;
    }
}
