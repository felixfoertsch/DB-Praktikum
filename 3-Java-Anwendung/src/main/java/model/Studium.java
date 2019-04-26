package model;

import java.time.Year;

public class Studium {
    Integer studiengangId;
    Integer studentId;
    private Year imma;
    private Year exma;
    private Integer semester;
    private Studiengang studiengang;

    public Studium(String imma, String exma, String semester, Studiengang studiengang) {
        this.imma = Year.parse(imma);
        this.exma = Year.parse(exma);
        this.semester = Integer.valueOf(semester);
        this.studiengang = studiengang;
    }
}
