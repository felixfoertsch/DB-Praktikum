import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

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

    public Collection<File> importArrayWrappedSingleCSV() {
        Collection<File> csvFiles = new ArrayList<>();

        FileChooser fileChooser = new FileChooser();

        Stage newStage = new Stage();
        csvFiles.add(fileChooser.showOpenDialog(newStage));

        return csvFiles;
    }

    public Universitaet parseCSVandCreateModel(Collection<File> files) {
        Universitaet universitaet = new Universitaet();
        ArrayList<Student> studenten = universitaet.getStudenten();
        ArrayList<Mitarbeiter> mitarbeiter = universitaet.getMitarbeiter();
        ArrayList<Veranstaltung> veranstaltungen = universitaet.getVeranstaltungen();
        ArrayList<Klausur> klausuren = universitaet.getKlausuren();

        for (File csv : files) {
            try {
                switch (csv.getName()) {
                    case "klausur_aufgaben.csv":
                        importKlausurAufgaben(csv);
                        break;
                    case "klausuren.csv":
                        importKlausuren(csv);
                        break;
                    case "klausurerg.csv":
                        importKlausurErg(csv);
                        break;
                    case "semprakerg.csv":
                        importSemPrakErg(csv);
                        break;
                    case "staff.csv":
                        importStaff(csv);
                        break;
                    case "student.csv":
                        importStudent(csv, studenten);
                        break;
                    case "veranstaltungen.csv":
                        importVeranstaltungen(csv, veranstaltungen, mitarbeiter);
                        break;
                    default:
                        importPunkte(csv);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        return universitaet;
    }
    
    private void importKlausurAufgaben(File csv) throws Exception {
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
    
    private void importKlausuren(File csv) throws Exception {
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


    }
    
    private void importKlausurErg(File csv) throws Exception {
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
    
    private void importSemPrakErg(File csv) throws Exception {
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
    
    private void importStaff(File csv) throws Exception {
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
    
    private void importStudent(File csv, ArrayList<Student> studenten) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> student = CSVFormat.RFC4180.withHeader(
                "Matrikel",
                "Nachname",
                "Vorname",
                "Email",
                "Studiengang",
                "Abschluss",
                "Regelstudienzeit",
                "Imma",
                "Exma",
                "Semester"
        ).parse(in);
        for (CSVRecord record : student) {
            System.out.println(record.toString());
            Studiengang studiengang = new Studiengang(record.get("Studiengang"), record.get("Abschluss"), record.get("Regelstudienzeit"));
            Studium studium = new Studium(record.get("Imma"), record.get("Exma"), record.get("Semester"), studiengang);
            Student s = new Student(record.get("Matrikel"), record.get("Vorname"), record.get("Nachname"), record.get("Email"), studium);
            studenten.add(s);
        }
    }
    
    private void importVeranstaltungen(File csv, ArrayList<Veranstaltung> va, ArrayList<Mitarbeiter> ma) throws Exception {
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
            Veranstaltung v;
            switch (record.get("typ")) {
                case "V":
                    v = new Grundvorlesung();
                    break;
                case "SV":
                    v = new Spezialvorlesung();
                    break;
                case "P":
                    v = new Praktikum();
                    break;
                case "PS":
                    v = new Problemseminar();
                    break;
                case "OS":
                    v = new Oberseminar();
                    break;
                case "Ü":
                    v = new Uebung();
                    break;
                default:
                    v = new Veranstaltung();
            }
            Mitarbeiter mitarbeiter = new Mitarbeiter();
            for (Mitarbeiter m : ma) {
                if (m.getNachname().equals(record.get("name"))) {
                    mitarbeiter = m;
                    break;
                }
                mitarbeiter.setNachname(record.get("name"));
            }
            v.setData(record.get("name"), record.get("jahr"), record.get("semester"), record.get("maxTeilnehmer"), mitarbeiter);
        }
    }

    private void importPunkte(File csv) throws Exception {
        String VLKenning = csv.getName();
    }
}
