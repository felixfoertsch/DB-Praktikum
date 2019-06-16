package model.person;

import model.relationen.AufgabenBearbeitung;
import model.relationen.KlausurTeilnahme;
import model.relationen.SeminarTeilnahme;
import model.relationen.Studium;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue
    private Integer id;

    @NaturalId
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;

    @Transient
    private Map<String, KlausurTeilnahme> klausurTeilnahmen;
    @Transient
    private Map<String, SeminarTeilnahme> semPrakTeilnahme;
    @Transient
    private Map<String, Studium> studiumMap;
    @Transient
    private Map<String, AufgabenBearbeitung> aufgabenBearbeitungen;

    public Student() {
    }

}
