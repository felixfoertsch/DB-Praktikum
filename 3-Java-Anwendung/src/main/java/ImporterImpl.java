import javafx.stage.DirectoryChooser;
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
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.*;

public class ImporterImpl implements Importer {

    public Map<String, File> importCSVtoMemory() {
        Map<String, File> csvFiles = new HashMap<>();

        DirectoryChooser directoryChooser = new DirectoryChooser();

        Stage newStage = new Stage();
        File path = directoryChooser.showDialog(newStage);

        if (path != null) {
            File[] listOfFiles = path.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("Added file " + listOfFiles[i].getName());
                    csvFiles.put(listOfFiles[i].getName(), listOfFiles[i]);
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Added directory " + listOfFiles[i].getName());
                    csvFiles.put(listOfFiles[i].getName(), listOfFiles[i]);
                }
            }
        } else {
            System.out.println("Path is null.");
        }

        System.out.println(Arrays.toString(csvFiles.entrySet().toArray()));

        //Map<String, File> kp = retrievePunkte(csvFiles.get("klausurpunkte"));

        return csvFiles;
    }

    public Collection<File> importArrayWrappedSingleCSV() {
        Collection<File> csvFiles = new ArrayList<>();

        FileChooser fileChooser = new FileChooser();

        Stage newStage = new Stage();
        csvFiles.add(fileChooser.showOpenDialog(newStage));

        return csvFiles;
    }

    // Reihenfolge: Student, Mitarbeiter
    public Universitaet parseCSVandCreateModel(Map<String, File> files) {
        Universitaet universitaet = new Universitaet();
        Map<String, Student> studenten = universitaet.getStudenten();
        Map<String, Mitarbeiter> mitarbeiter = universitaet.getMitarbeiter();
        Map<String, Veranstaltung> veranstaltungen = universitaet.getVeranstaltungen();
        Map<String, Klausur> klausuren = universitaet.getKlausuren();

        try {
            importStaff(files.get("staff.csv"), mitarbeiter);
            importStudent(files.get("student.csv"), studenten);

            importKlausuren(files.get("klausuren.csv"));
            importKlausurAufgaben(files.get("klausur_aufgaben.csv"));
            importKlausurErg(files.get("klausurerg.csv"));
            importSemPrakErg(files.get("semprakerg.csv"));
            importVeranstaltungen(files.get("veranstaltungen.csv"), veranstaltungen, mitarbeiter);
            importPunkte();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Import failed!");
        }

        return universitaet;
    }
    
    private void importKlausurAufgaben(File csv) throws Exception {
    public Map<String, File> retrievePunkte(File folder) {

        Map<String, File> punkte = new HashMap<>();

        File[] listOfFiles = folder.listFiles();

        if (folder != null) {

            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("Added file " + listOfFiles[i].getName());
                    punkte.put(listOfFiles[i].getName(), listOfFiles[i]);
                }
            }
        } else {
            System.out.println("Path is null.");
        }

        System.out.println(Arrays.toString(punkte.entrySet().toArray()));

        return punkte;
    }

    public void parseCSVandImportToDataSource(Map<String, File> files, DataSource dataSource) {

        /*for (File csv : files) {
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

        }*/
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
    
    private void importKlausuren(File csv, Map<String, Mitarbeiter> mitarbeiter) throws Exception {
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
            Klausur k;
            switch (record.get("Typ")){
                case "AK":
                    k = new Abschlussklausur();
                    break;
                case "ZK":
                    k = new Zwischenklausur();
                    break;
                case "WDH":
                    k = new Wiederholungsklausur();
                    break;
                default:
                    k = new Klausur();
            }
            System.out.println(record.toString());
            k.setDatum(record.get("datum"));
            k.setUhrzeitVon(record.get("uhrzeitVon"));
            k.setGesamtpunktzahl(record.get("gesamtpunktzahl"));
        }
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
    
    private void importStaff(File csv, Map<String, Mitarbeiter> mitarbeiter) throws Exception {
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
            Raum raum = new Raum(record.get("raum"));
            // Vorname und Nachname sind im Datensatz vertauscht
            Mitarbeiter m = new Mitarbeiter(record.get("nachname"), record.get("vorname"), record.get("mail"), record.get("titel"), raum);
            mitarbeiter.put(m.getNachname(), m);
        }
    }
    
    private void importStudent(File csv, Map<String, Student> studenten) throws Exception {
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
            studenten.put(s.getMatrikelNr(), s);
        }
    }
    
    private void importVeranstaltungen(File csv, Map<String, Veranstaltung> veranstaltungen, Map<String, Mitarbeiter> ma) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> veranstaltungenCSV = CSVFormat.RFC4180.withHeader(
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
        for (CSVRecord record : veranstaltungenCSV) {
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
            v.setData(record.get("name"), record.get("jahr"), record.get("semester"), record.get("maxTeilnehmer"), record.get("kennung"));
            getMitarbeiter(record.get("dozent"), ma;
        }
    }

    private void importPunkte(File csv) throws Exception {
        String VLKenning = csv.getName();
    }

    private Collection<Mitarbeiter> getMitarbeiter(String header, Map<String, Mitarbeiter> mitarbeiterListe) {
        // Take a string and look through mitarbeiterListe with the instantiated mitarbeiter objects to fetch the objects (lookup by nachname)
        if (header.contains(",")) {
            // "Junghanns, Christen" -> Split up String and return Collection with seperated objects
        }
        return
    }
}
