package model;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;

    @OneToMany(mappedBy = "student")
    @MapKey(name = "klausurTeilnahmeKey")
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;

    private Map<String, SemPrakTeilnahme> semPrakTeilnahme;

    private Map<String, Studium> studiumMap;

    @OneToMany(mappedBy = "aufgabenBearbeitung")
    @MapKey(name = "aufgabenBearbeitungKey")
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Student() {
    }

}
