package model;

import java.time.Year;

public class Studium {
    private Student student;
    private Studiengang studiengang;
    private Year imma;
    private Year exma;
    private Integer semester;

    public Studium(String imma, String exma, String semester, Studiengang studiengang) {
        this.imma = Year.parse(imma);
        this.exma = parseExma(exma);
        this.semester = Integer.valueOf(semester);
        this.studiengang = studiengang;
    }

    private Year parseExma(String exma) {
        if (exma.equals("NA")) {
            return null;
        }
        return Year.parse(exma);
    }

    public String generateKey(){
        return student.getMatrikelNr() + "_" + studiengang.getName();
    }
}
