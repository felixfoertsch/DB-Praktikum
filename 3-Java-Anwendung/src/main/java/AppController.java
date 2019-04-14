import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

public class AppController {
    public Button importButton = null;

    @FXML
    public void importButtonClicked(Event e) throws Exception {
        FileChooser fileChooser = new FileChooser();

        Stage newStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(newStage);
        importCSV(selectedFile);
    }

    private void importCSV(File csv) throws Exception {
        Reader in = new FileReader(csv);
        switch (csv.getName()) {
            case "klausur_aufgaben.csv":
                Iterable<CSVRecord> klausur_aufgaben = CSVFormat.RFC4180.withHeader(
                        "KlausurNr",
                        "aufgaben_nr",
                        "Punkte"
                ).parse(in);
                for (CSVRecord record : klausur_aufgaben) {
                    System.out.println(record.toString());
                }
                break;

            case "klausuren.csv":
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

                break;

            case "klausurerg.csv":
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
                break;

            case "semprakerg.csv":
                Iterable<CSVRecord> semprakerg = CSVFormat.RFC4180.withHeader(
                        "VKennung",
                        "Matrikelnr",
                        "Note"
                ).parse(in);
                for (CSVRecord record : semprakerg) {
                    System.out.println(record.toString());
                }
                break;

            case "staff.csv":
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
                break;

            case "student.csv":
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
                break;

            case "veranstaltungen.csv":
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
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + csv.getName());
        }
    }
}
