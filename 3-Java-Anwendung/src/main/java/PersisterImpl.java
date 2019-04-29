
import model.Student;
import model.Universitaet;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

public class PersisterImpl {

    public void persistUniversitaet(Universitaet universitaet) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbp", "dbp", "dbp");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully.");

            //Add things to DB
            persistStudents(universitaet.getStudenten(), c);
            System.out.println("Didn't die!");
            //stmt.executeUpdate(sql);

            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully.");
    }

    public void persistStudents(Map<String, Student> studenten, Connection c) throws Exception {
        Statement stmt = c.createStatement();
        for (Student s : studenten.values()) {
            String sql = String.format("INSERT INTO student " +
                    "(matrikelNr, vorname, nachname, uniMail) " +
                    "VALUES ('%s', '%s', '%s', '%s');", s.getMatrikelNr(), s.getVorname(), s.getNachname(), s.getUniMail());
            stmt.addBatch(sql);
            System.out.println(sql);
        }
        stmt.executeBatch();
        c.commit();
        stmt.close();
    }
}
