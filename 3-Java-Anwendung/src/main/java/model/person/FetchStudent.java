package model.person;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.io.Serializable;
import java.util.Objects;

public class FetchStudent implements Serializable {

    private Integer id;
    private String matrikelNr;
    private String vorname;
    private String nachname;
    private String uniMail;
    private Float score;

    public FetchStudent() {
    }

    public FetchStudent(Integer id, String matrikelNr, String vorname, String nachname, String uniMail, Float score) {
        this.id = id;
        this.matrikelNr = matrikelNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.uniMail = uniMail;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FetchStudent student = (FetchStudent) o;
        return Objects.equals(this.id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    /***********************************************************************************************
     *
     * Getters and Setters
     *
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(String matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getUniMail() {
        return uniMail;
    }

    public void setUniMail(String uniMail) {
        this.uniMail = uniMail;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
