import com.sun.javafx.binding.StringFormatter;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import java.util.*;

public class ImporterImpl implements Importer {

    /**
     * Interaction method. Displays a JavaFX directory chooser and loads all the CSV files into a Map with the file
     * name as the key.
     *
     * @return HashMap<K: Filename, V: CSV file>
     */
    public Map<String, File> importCSVtoMemory() {
        Map<String, File> csvFiles = new HashMap<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage newStage = new Stage();
        File path = directoryChooser.showDialog(newStage);

        if (path != null) {
            File[] listOfFiles = path.listFiles();
            assert listOfFiles != null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    System.out.println("Add file " + listOfFile.getName());
                    csvFiles.put(listOfFile.getName(), listOfFile);
                } else if (listOfFile.isDirectory()) {
                    System.out.println("Add directory " + listOfFile.getName());
                    csvFiles.put(listOfFile.getName(), listOfFile);
                }
            }
        } else {
            System.out.println("Path is null.");
        }
        System.out.println(Arrays.toString(csvFiles.entrySet().toArray()));
        return csvFiles;
    }

    /**
     * Retrieves the klausurpunkte folder as a Map from the directory chooser.
     *
     * @param folder klausurpunkte folder
     * @return <K: Filename, V: CSV file>
     */
    private Map<String, File> retrieveKlausurPunkteFolder(File folder) {
        Map<String, File> punkte = new HashMap<>();
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                System.out.println("Add file " + listOfFile.getName());
                punkte.put(listOfFile.getName(), listOfFile);
            }
        }
        System.out.println(Arrays.toString(punkte.entrySet().toArray()));
        return punkte;
    }

    /**
     * Main method of the Importer. Imports a folder of CSV files into an University object that contains all the data.
     *
     * @param files folder containing the CSV files to be imported
     * @return Universitaet object that contains all of the imported data, ready to be persisted
     */
    public Universitaet parseCSVandCreateModel(Map<String, File> files) {
        Universitaet universitaet = new Universitaet();
        Map<String, File> klausurpunkte = retrieveKlausurPunkteFolder(files.get("klausurpunkte"));

        try {
            importStaff(files.get("staff.csv"), universitaet.getMitarbeiter(), universitaet.getRaeume());
            importStudent(files.get("student.csv"), universitaet.getStudenten());
            importKlausuren(files.get("klausuren.csv"), universitaet.getMitarbeiter(), universitaet.getRaeume(), universitaet.getKlausuren());
            importKlausurAufgaben(files.get("klausur_aufgaben.csv"), universitaet.getKlausuren());
            importVeranstaltungen(files.get("veranstaltungen.csv"), universitaet.getVeranstaltungen(), universitaet.getMitarbeiter(), universitaet.getRaeume());
            importKlausurErg(files.get("klausurerg.csv"), universitaet.getKlausuren(), universitaet.getStudenten());
            importSemPrakErg(files.get("semprakerg.csv"), universitaet.getVeranstaltungen(), universitaet.getStudenten());
            importPunkte(klausurpunkte, universitaet.getKlausuren(), universitaet.getStudenten());
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Import failed!");
        }
        return universitaet;
    }

    /**
     * A Mitarbeiter contains his associated Raum object.
     *
     * @param csv            staff.csv
     * @param mitarbeiterMap the map of the Mitarbeiter of the Universitaet
     * @param raeumeMap      the map of the Raum of the Universitaet
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
        ).withSkipHeaderRecord().parse(in);
        int count = 0;
        for (CSVRecord record : staffCSV) {
            Raum raum;
            if (raeumeMap.containsKey(record.get("raum"))) {
                raum = raeumeMap.get(record.get("raum"));
            } else {
                raum = new Raum(record.get("raum"));
                raeumeMap.put(record.get("raum"), raum);
            }
            // Vorname und Nachname sind im Datensatz vertauscht
            Mitarbeiter m = new Mitarbeiter(record.get("nachname"), record.get("vorname"), record.get("mail"), record.get("titel"), raum);
            mitarbeiterMap.put(m.getNachname(), m);
            count++;
        }
        System.out.println("Mitarbeiter:" + count + "/21");
    }

    /**
     * A Student contains his associated Studiengang and Studium objects.
     *
     * @param csv        student.csv
     * @param studentMap the map of the Student of the Universitaet
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
        ).withSkipHeaderRecord().parse(in);
        int count = 0;
        for (CSVRecord record : studentCSV) {
            Studiengang studiengang = new Studiengang(record.get("Studiengang"), record.get("Abschluss"), record.get("Regelstudienzeit"));
            Studium studium = new Studium(record.get("Imma"), record.get("Exma"), record.get("Semester"), studiengang);
            Student s = new Student(record.get("Matrikel"), record.get("Vorname"), record.get("Nachname"), record.get("Email"), studium);
            studentMap.put(s.getMatrikelNr(), s);
            count++;
        }
        System.out.println("Studenten:" + count + "/861");
    }

    /**
     * Creates all the necessary Raum during operation and puts them into the provided Map<String, Raum>.
     * Uses the provided Map<String, Mitarbeiter> to associate the Klausur with the Mitarbeiter.
     *
     * @param csv            klausuren.csv
     * @param mitarbeiterMap the map of the Mitarbeiter of the Universitaet
     * @param raumMap        the map of the Raum of the Universitaet
     * @param klausurMap     the map of the Klausuren of the Universitaet
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
        ).withSkipHeaderRecord().parse(in);

        int count = 0;
        for (CSVRecord record : klausurenCSV) {
            Klausur k;
            switch (record.get("Typ")) {
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
            Collection<Mitarbeiter> mitarbeiter = getMitarbeiterByLastName(record.get("Aufsicht"), mitarbeiterMap);
            Collection<Raum> raeume = getRaumeByName(record.get("ort"), raumMap);
            k.setData(record.get("name"), record.get("datum"), record.get("uhrzeitVon"), record.get("Gesamtpunktzahl"), record.get("Punktzahl100"), record.get("VeranstKennung"), record.get("KlausurNr"), mitarbeiter, raeume);
            klausurMap.put(k.generateKey(), k);
            count++;
        }
        System.out.println("Klausuren:" + count + "/57");
    }

    /**
     * Puts the Aufgabe into the Map of Klausur. Requires that klausuren.csv has already been imported.
     *
     * @param csv        klausur_aufgaben.csv
     * @param klausurMap the map of the Klausuren of the Universitaet
     * @throws Exception rethrows FileNotFoundException
     */
    private void importKlausurAufgaben(File csv, Map<String, Klausur> klausurMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausur_aufgabenCSV = CSVFormat.DEFAULT.withHeader(
                "KlausurNr",
                "aufgaben_nr",
                "Punkte"
        ).withSkipHeaderRecord().withDelimiter(';').parse(in);
        int count = 0;
        for (CSVRecord record : klausur_aufgabenCSV) {
            String klausurNr = record.get("KlausurNr");
            // klausur_aufgaben has an unnecessary extra underscore on the third column for Zwischenklausuren (15_ws_dbs1_zk)
            // we remove this underscore here
            if (klausurNr.contains("zk")) {
                StringBuilder sb = new StringBuilder(klausurNr);
                sb.deleteCharAt(2);
                klausurNr = sb.toString();
            }
            Aufgabe aufgabe = new Aufgabe(record.get("KlausurNr"), record.get("aufgaben_nr"), record.get("Punkte"));
            if (klausurNr.equals("18ws_idbs2_wh")) {
                aufgabe.setKlausurNr("18ws_idbs2");
                klausurMap.get("18ws_idbs2").addAufgabe(aufgabe);
                System.out.println("Modified klausurNr of Aufgabe 18ws_idbs2_wh to 18ws_idbs2");
                count++;
                continue;
            }
            if (klausurNr.equals("18ws_cdm")) {
                aufgabe.setKlausurNr("18ws_dm");
                klausurMap.get("18ws_dm").addAufgabe(aufgabe);
                System.out.println("Modified klausurNr of Aufgabe 18ws_cdm to 18ws_dm");
                count++;
                continue;
            }
            if (klausurNr.equals("16ws_idbs2_wh")) {
                aufgabe.setKlausurNr("15ws_idbs2_wh");
                klausurMap.get("15ws_idbs2_wh").addAufgabe(aufgabe);
                System.out.println("Modified klausurNr of Aufgabe 16ws_idbs2_wh to 15ws_idbs2_wh");
                count++;
                continue;
            }
            if (klausurMap.get(klausurNr) == null) {
                System.out.println("No Klausur with klausurNr " + klausurNr);
            }
            klausurMap.get(klausurNr).addAufgabe(aufgabe);
            count++;
        }
        System.out.println("Klausuraufgaben:" + count + "/387");
    }

    /**
     * Creates all Veranstaltung and puts them into the corresponding Map. Associates all Mitarbeiter and Raum
     * with the Veranstaltung. If a Veranstaltung is a Uebung, it gets associated with its corresponding Grundvorlesung.
     *
     * @param csv              veranstaltungen.csv
     * @param veranstaltungMap the Map of the Veranstaltungen of the Universitaet
     * @param mitarbeiterMap   the map of the Mitarbeiter of the Universitaet
     * @param raumMap          the map of the Raum of the Universitaet
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
        ).withSkipHeaderRecord().parse(in);
        int count = 0;
        for (CSVRecord record : veranstaltungenCSV) {
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
                    record.get("typ"),
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
            count++;
        }
        System.out.println("Veranstaltungen:" + count + "/83");
    }

    /**
     * Creates KlausurTeilnahme and puts them into its correspondig Klausur as well as Student.
     *
     * @param csv        klausurerg.csv
     * @param klausurMap the map of the Klausuren of the Universitaet
     * @param studentMap the map of the Student of the Universitaet
     * @throws Exception rethrows FileNotFoundException
     */
    private void importKlausurErg(File csv, Map<String, Klausur> klausurMap, Map<String, Student> studentMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> klausurergCSV = CSVFormat.RFC4180.withHeader(
                "KlausurNr",
                "Typ",
                "Matrikelnr",
                "NichtErschienen",
                "Entschuldigt",
                "Punkte",
                "Note"
        ).withSkipHeaderRecord().parse(in);
        int count = 0;
        for (CSVRecord record : klausurergCSV) {
            Klausur k = klausurMap.get(record.get("KlausurNr"));
            Student s = studentMap.get(record.get("Matrikelnr"));
            if (k == null || s == null) {
                continue;
            }
            KlausurTeilnahme kt = new KlausurTeilnahme(
                    k,
                    record.get("Typ"),
                    s,
                    record.get("NichtErschienen"),
                    record.get("Entschuldigt"),
                    record.get("Punkte"),
                    record.get("Note")
            );
            k.addKlausurTeilnahme(kt);
            s.addKlausurTeilnahme(kt);
            count++;
        }
        System.out.println("Klausurergebnisse:" + count + "/1866");
    }

    /**
     * Creates PraktikumTeilnahme and puts them into its correspondig Praktikum as well as Student.
     *
     * @param csv              semprakerg.csv
     * @param veranstaltungMap the Map of the Veranstaltungen of the Universitaet
     * @param studentMap       the map of the Student of the Universitaet
     * @throws Exception rethrows FileNotFoundException
     */
    private void importSemPrakErg(File csv, Map<String, Veranstaltung> veranstaltungMap, Map<String, Student> studentMap) throws Exception {
        Reader in = new FileReader(csv);
        Iterable<CSVRecord> semprakergCSV = CSVFormat.RFC4180.withHeader(
                "VKennung",
                "Matrikelnr",
                "Note"
        ).withSkipHeaderRecord().parse(in);
        int count = 0;
        for (CSVRecord record : semprakergCSV) {
            Veranstaltung veranstaltung = veranstaltungMap.get(record.get("VKennung"));
            Student student = studentMap.get(record.get("Matrikelnr"));
            if (student == null) {
                System.out.println("Student mit " + record.get("Matrikelnr") + "konnte nicht gefunden werden.");
                continue;
            }
            if (record.get("VKennung").contains("prak")) {
                Praktikum praktikum = (Praktikum) veranstaltung;
                if (praktikum == null) {
                    System.out.println("Praktikum " + record.get("VKennung") + "konnte nicht gefunden werden.");
                    continue;
                }
                PraktikumTeilnahme praktikumTeilnahme = new PraktikumTeilnahme(praktikum, student, record.get("Note"));
                praktikum.addPraktikumTeilnahme(praktikumTeilnahme);
                student.addPraktikumTeilnahme(praktikumTeilnahme);
            } else if (record.get("VKennung").contains("sem")) {
                Seminar seminar = (Seminar) veranstaltung;
                if (seminar == null) {
                    System.out.println("Seminar " + record.get("VKennung") + "konnte nicht gefunden werden.");
                    continue;
                }
                SeminarTeilnahme seminarTeilnahme = new SeminarTeilnahme(seminar, student, record.get("Note"));
                seminar.addSeminarTeilnahme(seminarTeilnahme);
                student.addSeminarTeilnahme(seminarTeilnahme);
            }
            count++;
        }
        System.out.println("Semprak-Ergebnisse:" + count + "/353");
    }

    /**
     * Iterates over the klausurpunkte folder, creates AufgabenBearbeitung objects and puts them into their corresponding
     * Student and Aufgabe objects. The Aufgabe objects are fetched from their corresponding Klausur.
     *
     * @param klausurpunkteFolder the provided data folder of CSV files
     * @param klausurMap          the map of the Klausuren of the Universitaet
     * @param studentMap          the map of the Student of the Universitaet
     * @throws Exception
     */
    private void importPunkte(Map<String, File> klausurpunkteFolder, Map<String, Klausur> klausurMap, Map<String, Student> studentMap) throws Exception {
        for (Map.Entry<String, File> entry : klausurpunkteFolder.entrySet()) {
            Reader in = new FileReader(entry.getValue());
            Iterable<CSVRecord> punkteCSV = CSVFormat.RFC4180.withHeader(
                    "Matrikel",
                    "KlausurNr",
                    "Punkte"
            ).withSkipHeaderRecord().parse(in);
            // This for-loop is the actual CSV file that is going to be parsed. Punkte is a sorted List of items (eg [6,3,7,6,0.5,0,0])
            // that has to be split.
            for (CSVRecord record : punkteCSV) {
                Klausur klausur = klausurMap.get(record.get("KlausurNr"));
                Student student = studentMap.get(record.get("Matrikel"));
                if (klausur == null || student == null) {
                    continue;
                }
                int index = 1;
                for (String string : splitString(record.get("Punkte"))) {
                    Aufgabe aufgabe = klausur.getAufgabeByIndex(index);
                    AufgabenBearbeitung ab = new AufgabenBearbeitung(aufgabe, student, string);
                    aufgabe.addAufgabenBearbeitung(ab);
                    student.addAufgabenBearbeitung(ab);
                }
            }
        }
    }

    /**
     * Helper method. Takes a String that may contain multiple Mitarbeiter, splits it and gets the Mitarbeiter from the
     * provided map, if it exists. Requires that staff.csv is already imported.
     *
     * @param toSplit
     * @param map
     * @return
     */
    private Collection<Mitarbeiter> getMitarbeiterByLastName(String toSplit, Map<String, Mitarbeiter> map) {
        Collection<Mitarbeiter> mitarbeiter = new ArrayList<>();
        for (String string : splitString(toSplit)) {
            if (string.equals("NULL")) {
                break;
            }
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
     *
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
     *
     * @param toSplit
     * @return
     */
    private String[] splitString(String toSplit) {
        if (toSplit.contains("Groß. Christen")) {
            toSplit = toSplit.replace(".", ",");
        }
        if (toSplit.contains(",")) {
            // "Junghanns, Christen" -> Split up String and fetch seperate object for each string
            String withoutWhitespace = toSplit.replace(" ", "");
            return withoutWhitespace.split(",");
        } else {
            return new String[]{toSplit};
        }
    }

    public void persistModel(Universitaet universitaet) {
        Connection c = null;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbp", "dbp", "dbp");
            c.setAutoCommit(false);
            System.out.println("Opened database connection successfully");

            persistKlausuren(universitaet, c);
            persistVeranstaltungen(universitaet, c);
            persistRaum(universitaet, c);
            persistMitarbeiter(universitaet, c);
            persistStudent(universitaet, c);
            persistKlausurAufgaben(universitaet, c);

            c.commit();
            c.close();
            System.out.println("Closed database connection successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    private void persistKlausuren(Universitaet universitaet, Connection c) throws Exception {
        Map<String, Klausur> klausurMap = universitaet.getKlausuren();
        String insert = "INSERT INTO klausur (datum, uhrzeitVon, gesamtpunktzahl) VALUES (?, ?, ?)";
        PreparedStatement insertKlausur = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

        for (Klausur klausur : klausurMap.values()) {
            insertKlausur.setObject(1, klausur.getDatum());
            insertKlausur.setObject(2, klausur.getUhrzeitVon());
            insertKlausur.setObject(3, klausur.getGesamtpunktzahl());
            insertKlausur.executeUpdate();
            ResultSet rs = insertKlausur.getGeneratedKeys();
            rs.next();
            klausur.setId(rs.getInt(1));

            String typ;
            if (klausur.getTyp().equals("zk")) {
                typ = "INSERT INTO zwischenklausur (klausurId) VALUES (?)";
            } else {
                typ = "INSERT INTO abschlussklausur (klausurId) VALUES (?)";
            }
            PreparedStatement insertKlausurTyp = c.prepareStatement(typ);
            insertKlausurTyp.setObject(1, klausur.getId());
            insertKlausurTyp.executeUpdate();
            insertKlausurTyp.close();

            if (klausur.getTyp().equals("wh")) {
                typ = "INSERT INTO wiederholungsklausur (klausurId) VALUES (?)";
                PreparedStatement insertWdh = c.prepareStatement(typ);
                insertWdh.setObject(1, klausur.getId());
                insertWdh.executeUpdate();
                insertWdh.close();
            }
        }
        insertKlausur.close();
    }

    private void persistVeranstaltungen(Universitaet universitaet, Connection c) throws Exception {
        Map<String, Veranstaltung> veranstaltungMap = universitaet.getVeranstaltungen();
        String insert = "INSERT INTO veranstaltung (name, jahr, semester, maxTeilnehmer) VALUES (?, ?, ?, ?)";
        PreparedStatement insertVeranstaltung = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

        ArrayList<Uebung> uebungen = new ArrayList<>();

        for (Veranstaltung veranstaltung : veranstaltungMap.values()) {
            insertVeranstaltung.setObject(1, veranstaltung.getName());
            insertVeranstaltung.setObject(2, veranstaltung.getJahr());
            insertVeranstaltung.setObject(3, veranstaltung.getSemester());
            insertVeranstaltung.setObject(4, veranstaltung.getMaxTeilnehmer());
            insertVeranstaltung.executeUpdate();
            ResultSet rs = insertVeranstaltung.getGeneratedKeys();
            rs.next();
            veranstaltung.setId(rs.getInt(1));

            String typ;
            if (veranstaltung.getTyp().equals("V")) {
                typ = "INSERT INTO grundvorlesung (veranstaltungId) VALUES (?)";
                PreparedStatement insertVATyp = c.prepareStatement(typ);
                insertVATyp.setObject(1, veranstaltung.getId());
                insertVATyp.executeUpdate();
                insertVATyp.close();
            } else if (veranstaltung.getTyp().equals("SV")) {
                typ = "INSERT INTO spezialvorlesung (veranstaltungId) VALUES (?)";
                PreparedStatement insertVATyp = c.prepareStatement(typ);
                insertVATyp.setObject(1, veranstaltung.getId());
                insertVATyp.executeUpdate();
                insertVATyp.close();
            } else if (veranstaltung.getTyp().equals("P")) {
                typ = "INSERT INTO praktikum (veranstaltungId) VALUES (?)";
                PreparedStatement insertVATyp = c.prepareStatement(typ);
                insertVATyp.setObject(1, veranstaltung.getId());
                insertVATyp.executeUpdate();
                insertVATyp.close();
            } else if (veranstaltung.getTyp().equals("OS") || veranstaltung.getTyp().equals("PS")) {
                typ = "INSERT INTO seminar (veranstaltungId) VALUES (?)";
                PreparedStatement insertVATyp = c.prepareStatement(typ);
                insertVATyp.setObject(1, veranstaltung.getId());
                insertVATyp.executeUpdate();
                insertVATyp.close();
                if (veranstaltung.getTyp().equals("OS")) {
                    typ = "INSERT INTO oberseminar (veranstaltungId) VALUES (?)";
                    PreparedStatement insertSeminarTyp = c.prepareStatement(typ);
                    insertSeminarTyp.setObject(1, veranstaltung.getId());
                    insertSeminarTyp.executeUpdate();
                    insertSeminarTyp.close();
                } else {
                    typ = "INSERT INTO problemseminar (veranstaltungId) VALUES (?)";
                    PreparedStatement insertSeminarTyp = c.prepareStatement(typ);
                    insertSeminarTyp.setObject(1, veranstaltung.getId());
                    insertSeminarTyp.executeUpdate();
                    insertSeminarTyp.close();
                }
            } else if (veranstaltung.getTyp().equals("Ü")) {
                uebungen.add((Uebung) veranstaltung);
            }
        }

        for (Uebung uebung : uebungen) {
            Veranstaltung zugehoerigeGV = veranstaltungMap.get(uebung.getKennung());
            String typ = "INSERT INTO uebung (veranstaltungId, grundvorlesungid) VALUES (?, ?)";
            PreparedStatement insertUebung = c.prepareStatement(typ);
            insertUebung.setObject(1, uebung.getId());
            insertUebung.setObject(2, zugehoerigeGV.getId());
            insertUebung.executeUpdate();
            insertUebung.close();
        }
        insertVeranstaltung.close();
    }

    private void persistRaum(Universitaet universitaet, Connection c) throws Exception {
        Map<String, Raum> raumMap = universitaet.getRaeume();
        String insert = "INSERT INTO raum (bezeichnung) VALUES (?)";
        PreparedStatement insertRaum = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        for (Raum raum : raumMap.values()) {
            insertRaum.setObject(1, raum.getBezeichnung());
            insertRaum.executeUpdate();
            ResultSet rs = insertRaum.getGeneratedKeys();
            rs.next();
            raum.setId(rs.getInt(1));
        }
        insertRaum.close();
    }

    private void persistMitarbeiter(Universitaet universitaet, Connection c) throws Exception {
        Map<String, Mitarbeiter> mitarbeiterMap = universitaet.getMitarbeiter();

        // TODO: pull out prepared Statement
        for (Mitarbeiter mitarbeiter : mitarbeiterMap.values()) {
            String insert = "INSERT INTO mitarbeiter (vorname, nachname, email, raumId) VALUES (?, ?, ?, ?)";
            PreparedStatement insertMitarbeiter = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            insertMitarbeiter.setObject(1, mitarbeiter.getVorname());
            insertMitarbeiter.setObject(2, mitarbeiter.getNachname());
            insertMitarbeiter.setObject(3, mitarbeiter.getEmail());
            insertMitarbeiter.setObject(4, mitarbeiter.getRaum().getId());
            insertMitarbeiter.executeUpdate();
            ResultSet rs = insertMitarbeiter.getGeneratedKeys();
            rs.next();
            mitarbeiter.setId(rs.getInt(1));
            insertMitarbeiter.close();

//            String insertRaum = "UPDATE raum SET mitarbeiterid = ? WHERE id = ?";
//            PreparedStatement insertMitarbeiterInRaum = c.prepareStatement(insertRaum);
//            insertMitarbeiterInRaum.setObject(1, mitarbeiter.getId());
//            insertMitarbeiterInRaum.setObject(2, mitarbeiter.getRaum().getId());
//            insertMitarbeiterInRaum.executeUpdate();
//            insertMitarbeiterInRaum.close();
        }

    }

    private void persistStudent(Universitaet universitaet, Connection c) throws Exception {
        Map<String, Student> studentMap = universitaet.getStudenten();
        String insert = "INSERT INTO student (matrikelNr, vorname, nachname, uniMail) VALUES (?, ?, ?, ?)";
        PreparedStatement insertStudent = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

        for (Student student : studentMap.values()) {
            insertStudent.setObject(1, student.getMatrikelNr());
            insertStudent.setObject(2, student.getVorname());
            insertStudent.setObject(3, student.getNachname());
            insertStudent.setObject(4, student.getUniMail());
            insertStudent.executeUpdate();
            ResultSet rs = insertStudent.getGeneratedKeys();
            rs.next();
            student.setId(rs.getInt(1));
        }
        insertStudent.close();
    }

    private void persistKlausurAufgaben(Universitaet universitaet, Connection c) throws Exception {
        Map<String, Klausur> klausurMap = universitaet.getKlausuren();
        String insert = "INSERT INTO aufgabe (klausurid, rang, maxpunkte) VALUES (?, ?, ?)";
        PreparedStatement insertAufgabe = c.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

        for (Klausur klausur : klausurMap.values()) {
            for (Aufgabe aufgabe : klausur.getAufgaben().values()) {
                insertAufgabe.setObject(1, klausur.getId());
                insertAufgabe.setObject(2, aufgabe.getAufgabenNr());
                insertAufgabe.setObject(3, aufgabe.getMaxPunkte());
                insertAufgabe.executeUpdate();
                ResultSet rs = insertAufgabe.getGeneratedKeys();
                rs.next();
                aufgabe.setId(rs.getInt(1));
            }
        }
        insertAufgabe.close();
    }

}
