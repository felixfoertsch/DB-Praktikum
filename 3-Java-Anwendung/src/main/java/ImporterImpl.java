import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;

public class ImporterImpl implements Importer {

    public Collection<File> importCSVtoMemory() {
        Collection<File> csvFiles = new ArrayList<>();
        FileChooser fileChooser = new FileChooser();

        Stage newStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(newStage);

        return csvFiles;
    }

    public void parseCSVandImportToDataSource(Collection<File> files, DataSource dataSource) {

        for (File csv : files) {
            try {
                switch (csv.getName()) {
                    case "klausur_aufgaben.csv":
                        importKlausurAufgaben(csv, dataSource);
                        break;
                    case "klausuren.csv":
                        importKlausuren(csv, dataSource);
                        break;
                    case "klausurerg.csv":
                        importKlausurErg(csv, dataSource);
                        break;
                    case "semprakerg.csv":
                        importSemPrakErg(csv, dataSource);
                        break;
                    case "staff.csv":
                        importStaff(csv, dataSource);
                        break;
                    case "student.csv":
                        importStudent(csv, dataSource);
                        break;
                    case "veranstaltungen.csv":
                        importVeranstaltungen(csv, dataSource);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + csv.getName());
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
    }
    
    private void importKlausurAufgaben(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausur_aufgaben = CSVFormat.RFC4180.withHeader(
                "KlausurNr",
                "aufgaben_nr",
                "Punkte"
        ).parse(in);
        for (CSVRecord record : klausur_aufgaben) {
            System.out.println(record.toString());
        }
    }
    
    private void importKlausuren(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausuren = CSVFormat.RFC4180.withHeader(
                "datum",
                "name",
                "ort",
                "uhrzeitVon",
                "Punktzahl100",
                "Gesamtpunktzahl",
                "Aufsicht",
                "VeranstKennung",
                "Typ",
                "KlausurNr"
        ).parse(in);
        for (CSVRecord record : klausuren) {
            System.out.println(record.toString());
        }
    }
    
    private void importKlausurErg(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausurerg = CSVFormat.RFC4180.withHeader(
                "KlausurNr",
                "Typ",
                "Matrikelnr",
                "NichtErschienen",
                "Entschuldigt",
                "Punkte",
                "Note"
        ).parse(in);
        for (CSVRecord record : klausurerg) {
            System.out.println(record.toString());
        }
    }
    
    private void importSemPrakErg(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> semprakerg = CSVFormat.RFC4180.withHeader(
                "VKennung",
                "Matrikelnr",
                "Note"
        ).parse(in);
        for (CSVRecord record : semprakerg) {
            System.out.println(record.toString());
        }
    }
    
    private void importStaff(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> staff = CSVFormat.RFC4180.withHeader(
                "vorname",
                "nachname",
                "titel",
                "raum",
                "mail"
        ).parse(in);
        for (CSVRecord record : staff) {
            System.out.println(record.toString());
        }
    }
    
    private void importStudent(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> student = CSVFormat.RFC4180.withHeader(
                "Matrikel",
                "Nachname",
                "Vorname",
                "Email",
                "Abschluss",
                "Regelstudienzeit",
                "Imma",
                "Exma",
                "Semester"
        ).parse(in);
        for (CSVRecord record : student) {
            System.out.println(record.toString());
        }
    }
    
    private void importVeranstaltungen(File csv, DataSource dataSource) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> veranstaltungen = CSVFormat.RFC4180.withHeader(
                "typ",
                "name",
                "jahr",
                "semester",
                "raum",
                "maxTeilnehmer",
                "dozent",
                "zeit",
                "tag",
                "kennung"
        ).parse(in);
        for (CSVRecord record : veranstaltungen) {
            System.out.println(record.toString());
        }
    }
}
