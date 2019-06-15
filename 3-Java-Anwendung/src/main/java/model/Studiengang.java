package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "studiengang")
public class Studiengang {

    @Id
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    @OneToMany
    private Map<String, Studium> studium;

    public Studiengang() {
    }

}
