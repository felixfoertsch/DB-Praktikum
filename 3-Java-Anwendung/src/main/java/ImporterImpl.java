import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    public Universitaet parseCSVandCreateModel(Map<String, File> files) {
        Universitaet universitaet = new Universitaet();

        try {
            importStaff(files.get("staff.csv"), universitaet.getMitarbeiter(), universitaet.getRaeume());
            importStudent(files.get("student.csv"), universitaet.getStudenten());
            importKlausuren(files.get("klausuren.csv"), universitaet.getMitarbeiter(), universitaet.getRaeume(), universitaet.getKlausuren());
            importKlausurAufgaben(files.get("klausur_aufgaben.csv"), universitaet.getKlausuren());
            importVeranstaltungen(files.get("veranstaltungen.csv"), universitaet.getVeranstaltungen(), universitaet.getMitarbeiter(), universitaet.getRaeume());

            importKlausurErg(files.get("klausurerg.csv"));
            importSemPrakErg(files.get("semprakerg.csv"));

            //importPunkte();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Import failed!");
        }

        return universitaet;
    }

    /**
     * A Mitarbeiter contains his associated Raum object.
     * @param csv staff.csv
     * @param mitarbeiterMap
     * @param raeumeMap
     * @throws Exception rethrows FileNotFoundException
     */
    private void importStaff(File csv, Map<String, Mitarbeiter> mitarbeiterMap, Map<String, Raum> raeumeMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> staffCSV = CSVFormat.RFC4180.withHeader(
                "vorname",
                "nachname",
                "titel",
                "raum",
                "mail"
        ).parse(in);
        for (CSVRecord record : staffCSV) {
            System.out.println(record.toString());
            Raum raum = new Raum(record.get("raum"));
            // Vorname und Nachname sind im Datensatz vertauscht
            raeumeMap.put(record.get("raum"), raum);
            Mitarbeiter m = new Mitarbeiter(record.get("nachname"), record.get("vorname"), record.get("mail"), record.get("titel"), raum);
            mitarbeiterMap.put(m.getNachname(), m);
        }
    }

    /**
     * A Student contains his associated Studiengang and Studium objects.
     * @param csv student.csv
     * @param studentMap
     * @throws Exception rethrows FileNotFoundException
     */
    private void importStudent(File csv, Map<String, Student> studentMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> studentCSV = CSVFormat.RFC4180.withHeader(
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
        for (CSVRecord record : studentCSV) {
            System.out.println(record.toString());
            Studiengang studiengang = new Studiengang(record.get("Studiengang"), record.get("Abschluss"), record.get("Regelstudienzeit"));
            Studium studium = new Studium(record.get("Imma"), record.get("Exma"), record.get("Semester"), studiengang);
            Student s = new Student(record.get("Matrikel"), record.get("Vorname"), record.get("Nachname"), record.get("Email"), studium);
            studentMap.put(s.getMatrikelNr(), s);
        }
    }

    /**
     * Creates all the necessary Raum during operation and puts them into the provided Map<String, Raum>.
     * Uses the provided Map<String, Mitarbeiter> to associate the Klausur with the Mitarbeiter.
     * @param csv klausuren.csv
     * @param mitarbeiterMap the map of the Mitarbeiter of the Universitaet
     * @param raumMap the map of the Raum of the Universitaet
     * @param klausurMap
     * @throws Exception rethrows FileNotFoundException
     */
    private void importKlausuren(File csv, Map<String, Mitarbeiter> mitarbeiterMap, Map<String, Raum> raumMap, Map<String, Klausur> klausurMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausurenCSV = CSVFormat.RFC4180.withHeader(
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

        for (CSVRecord record : klausurenCSV) {
            Klausur k;
            switch (record.get("Typ")){
                case "AK":
                    k = new Abschlussklausur();
                    k.setTyp("ak");
                    break;
                case "ZK":
                    k = new Zwischenklausur();
                    k.setTyp("zk");
                    break;
                case "WDH":
                    k = new Wiederholungsklausur();
                    k.setTyp("wh");
                    break;
                default:
                    k = new Klausur();
                    k.setTyp("ak");
            }
            System.out.println(record.toString());
            Collection<Mitarbeiter> mitarbeiter = getMitarbeiterByLastName(record.get("Aufsicht"), mitarbeiterMap);
            Collection<Raum> raeume = getRaumeByName(record.get("ort"), raumMap);
            k.setData(record.get("name"), record.get("datum"), record.get("uhrzeitVon"), record.get("Gesamtpunktzahl"), record.get("Punktzahl100"), record.get("VeranstKennung"), record.get("KlausurNr"), mitarbeiter, raeume);
            klausurMap.put(k.generateKey(), k);
        }
    }

    /**
     * Puts the Aufgabe into the Map of Klausur. Requires that klausuren.csv has already been imported.
     * @param csv klausur_aufgaben.csv
     * @param klausurMap the map of the Klausuren of the Universitaet
     * @throws Exception rethrows FileNotFoundException
     */
    private void importKlausurAufgaben(File csv, Map<String, Klausur> klausurMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausur_aufgabenCSV = CSVFormat.RFC4180.withHeader(
                "KlausurNr",
                "aufgaben_nr",
                "Punkte"
        ).parse(in);
        Map<Integer, Aufgabe> aufgabenMap = new HashMap<>();
        for (CSVRecord record : klausur_aufgabenCSV) {
            String klausurNr = record.get("KlausurNr");
            // klausur_aufgaben has an unnecessary extra underscore on the third column for Zwischenklausuren (15_ws_dbs1_zk)
            if (klausurNr.contains("zk")) {
                StringBuilder sb = new StringBuilder(klausurNr);
                sb.deleteCharAt(2);
                klausurNr = sb.toString();
            }
            Aufgabe aufgabe = new Aufgabe(record.get("aufgaben_nr"), record.get("Punkte"));
            klausurMap.get(klausurNr).addAufgabe(aufgabe);
        }
    }

    /**
     * Creates all Veranstaltung and puts them into the corresponding Map. Associates all Mitarbeiter and Raum
     * with the Veranstaltung. If a Veranstaltung is a Uebung, it gets associated with its corresponding Grundvorlesung.
     * @param csv veranstaltungen.csv
     * @param veranstaltungMap the Map of the Veranstaltungen of the Universitaet
     * @param mitarbeiterMap the map of the Mitarbeiter of the Universitaet
     * @param raumMap the map of the Raum of the Universitaet
     * @throws Exception rethrows FileNotFoundException
     */
    private void importVeranstaltungen(File csv, Map<String, Veranstaltung> veranstaltungMap, Map<String, Mitarbeiter> mitarbeiterMap, Map<String, Raum> raumMap) throws Exception {
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
            v.setData(
                    record.get("name"),
                    record.get("jahr"),
                    record.get("semester"),
                    getRaumeByName(record.get("raum"), raumMap).get(0),
                    record.get("maxTeilnehmer"),
                    record.get("zeit"),
                    record.get("tag"),
                    record.get("kennung")
            );
            for (Mitarbeiter m : getMitarbeiterByLastName(record.get("dozent"), mitarbeiterMap)) {
                v.addDozent(m);
            }
            if (v instanceof Uebung) {
                veranstaltungMap.get(v.getKennung());
            }
            veranstaltungMap.put(v.generateKey(), v);
        }
    }

    private void importKlausurErg(File csv) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausurergCSV = CSVFormat.RFC4180.withHeader(
                "KlausurNr",
                "Typ",
                "Matrikelnr",
                "NichtErschienen",
                "Entschuldigt",
                "Punkte",
                "Note"
        ).parse(in);
        for (CSVRecord record : klausurergCSV) {
            System.out.println(record.toString());
        }
    }

    private void importSemPrakErg(File csv) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> semprakergCSV = CSVFormat.RFC4180.withHeader(
                "VKennung",
                "Matrikelnr",
                "Note"
        ).parse(in);
        for (CSVRecord record : semprakergCSV) {
            System.out.println(record.toString());
        }
    }


    private Map<String, File> retrievePunkte(File folder) {
        Map<String, File> punkte = new HashMap<>();
        File[] listOfFiles = folder.listFiles();
        if (folder != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    System.out.println("Added file " + listOfFile.getName());
                    punkte.put(listOfFile.getName(), listOfFile);
                }
            }
        } else {
            System.out.println("Path is null.");
        }
        System.out.println(Arrays.toString(punkte.entrySet().toArray()));
        return punkte;
    }

    private void importPunkte(File csv) throws Exception {
        String VLKenning = csv.getName();
    }

    /**
     * Helper method. Takes a String that may contain multiple Mitarbeiter, splits it and gets the Mitarbeiter from the
     * provided map, if it exists. Requires that staff.csv is already imported.
     * @param toSplit
     * @param map
     * @return
     */
    private Collection<Mitarbeiter> getMitarbeiterByLastName(String toSplit, Map<String, Mitarbeiter> map) {
        Collection<Mitarbeiter> mitarbeiter = new ArrayList<>();
        for (String string : splitString(toSplit)) {
            if (string.equals("NULL")) { break; }
            if (map.get(string) == null) {
                System.out.println(string + " not found in MitarbeiterMap");
            } else {
                mitarbeiter.add(map.get(string));
            }
        }
        return mitarbeiter;
    }

    /**
     * Helper method. Takes a String that may contain multiple Raum, splits it and gets the Raum from the provided map.
     * Uses Map.putIfAbsent to create the Raum if it doesn't exists, since Raum is a simple object and is only
     * identified by Raum.bezeichnung.
     * @param toSplit
     * @param map
     * @return
     */
    private ArrayList<Raum> getRaumeByName(String toSplit, Map<String, Raum> map) {
        ArrayList<Raum> raeume = new ArrayList<>();
        for (String string : splitString(toSplit)) {
            raeume.add(map.putIfAbsent(string, new Raum(string)));
        }
        return raeume;
    }

    /**
     * Helper method. The data has some strings that have to be split into parts; this method takes such a string,
     * removes all white space and returns the split String as an Array.
     * @param toSplit
     * @return
     */
    private String[] splitString(String toSplit) {
        if (toSplit.contains(",")) {
            // "Junghanns, Christen" -> Split up String and fetch seperate object for each string
            String withoutWhitespace = toSplit.replace(" ", "");
            return withoutWhitespace.split(",");
        } else {
            return new String[]{toSplit};
        }
    }
}
