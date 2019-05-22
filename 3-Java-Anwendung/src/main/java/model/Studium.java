package model;

import java.time.Year;

public class Studium {
    Integer studiengangId;
    Integer studentId;
    private Integer imma;
    private Integer exma;
    private Integer semester;
    private Studiengang studiengang;

    public Studium(String imma, String exma, String semester, Studiengang studiengang) {
        this.imma = Integer.valueOf(imma);
        this.exma = parseExma(exma);
        this.semester = Integer.valueOf(semester);
        this.studiengang = studiengang;
    }

    private Integer parseExma(String exma) {
        if (exma.equals("NA")) {
            return null;
        }
        return Integer.valueOf(exma);
    }

    public Studiengang getStudiengang() {
        return studiengang;
    }

    public Integer getImma() {
        return imma;
    }

    public Integer getExma() {
        return exma;
    }

    public Integer getSemester() {
        return semester;
    }
}
