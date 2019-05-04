package model;

import javax.persistence.Id;
import java.time.Year;

public class Studium {

    @Id
    Integer studiengangId;
    Integer studentId;
    private Year imma;
    private Year exma;
    private Integer semester;
    private Studiengang studiengang;

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
}
