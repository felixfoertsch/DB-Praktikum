
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class PersisterImpl {
    public void dummy(ArrayList<CSVRecord> klausuren) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbp","dbp","dbp");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            for (CSVRecord record : klausuren) {

                System.out.println(record.toString());
                stmt = c.createStatement();

                String sql = String.format("INSERT INTO klausur" +
                        "(datum, uhrzeitvon, gesamtpunktzahl) " +
                        "VALUES (%s, %s, %s);", record.get("datum"), record.get("uhrzeitVon"), record.get("Gesamtpunktzahl"));
                stmt.executeUpdate(sql);

                stmt.close();
                c.commit();
                c.close();
            }

        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
