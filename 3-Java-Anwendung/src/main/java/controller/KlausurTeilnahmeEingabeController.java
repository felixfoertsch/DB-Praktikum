package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;
import model.Aufgabe;
import model.klausur.Klausur;
import model.person.Student;
import model.relationen.KlausurTeilnahme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.regex.Pattern;

public class KlausurTeilnahmeEingabeController {
    private SessionFactory sessionFactory;
    private Student selectedStudent;
    private Klausur selectedKlausur;
    private List<Aufgabe> aufgaben;

    // Form: KlausurTeilnahmeHinzufuegen
    @FXML
    GridPane klausurTeilnahmeEingabeGridPane;
    @FXML
    CheckBox klausurNotenEingabeErschienenCheckBox;
    @FXML
    CheckBox klausurNotenEingabeEntschuldigtCheckBox;
    @FXML
    Label klausurNotenEingabePunkteLabel;
    @FXML
    TextField klausurNotenEingabePunkteTextField;
    @FXML
    TextField klausurNotenEingabeNotenTextField;
    @FXML
    Button hinzufuegenButton;
    private Label punkteSummeLabel = new Label("");
    private Double gesamtpunkte = 0.0;

    void injectSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    void setupController(Student student, Klausur klausur) {
        this.selectedStudent = student;
        this.selectedKlausur = klausur;
        this.aufgaben = klausur.getAufgaben();

        if (aufgaben.isEmpty()) {
            Label keineAufgaben = new Label("Diese Klausur enthält keine Aufgaben.");
            klausurTeilnahmeEingabeGridPane.getChildren().remove(hinzufuegenButton);
            klausurTeilnahmeEingabeGridPane.add(keineAufgaben, 0, 5);
            klausurTeilnahmeEingabeGridPane.add(hinzufuegenButton, 0, 6);
        } else {
            klausurTeilnahmeEingabeGridPane.getChildren().remove(klausurNotenEingabePunkteTextField);
            klausurTeilnahmeEingabeGridPane.getChildren().remove(klausurNotenEingabePunkteLabel);
            klausurTeilnahmeEingabeGridPane.getChildren().remove(hinzufuegenButton);

            int rowcounter = 4;
            for (Aufgabe a : aufgaben) {
                Label tempLabel = new Label("Aufgabe " + a.getRang());
                TextField tempTextField = new TextField("0.0");
                tempTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                    gesamtpunkte = gesamtpunkte - Double.valueOf(oldValue);
                    gesamtpunkte = gesamtpunkte + Double.valueOf(newValue);
                    System.out.println(gesamtpunkte);
                    punkteSummeLabel.setText(gesamtpunkte.toString());
                });
                tempTextField.setTextFormatter(getDoubleFormatter());

                klausurTeilnahmeEingabeGridPane.add(tempLabel, 0, rowcounter);
                klausurTeilnahmeEingabeGridPane.add(tempTextField, 1, rowcounter);
                rowcounter++;
            }
            klausurTeilnahmeEingabeGridPane.add(klausurNotenEingabePunkteLabel, 0, rowcounter);
            klausurTeilnahmeEingabeGridPane.add(punkteSummeLabel, 1, rowcounter);
            klausurTeilnahmeEingabeGridPane.add(hinzufuegenButton, 0, rowcounter + 1);
        }
    }

    @FXML
    private void hinzufuegenButtonPressed() {
        KlausurTeilnahme kt = new KlausurTeilnahme();
        kt.setEntschuldigt(klausurNotenEingabeEntschuldigtCheckBox.isSelected());
        kt.setErschienen(klausurNotenEingabeErschienenCheckBox.isSelected());
        kt.setKlausur(selectedKlausur);
        kt.setStudent(selectedStudent);
        kt.setPunkte(Double.valueOf(klausurNotenEingabePunkteTextField.getText()));
        kt.setNote(Double.valueOf(klausurNotenEingabeNotenTextField.getText()));

        for (Aufgabe a : aufgaben) {

        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(kt);
        // if AufgabenBearbeitung is not null add it
        session.getTransaction().commit();
        session.close();
    }

    private TextFormatter<Double> getDoubleFormatter() {
        Pattern validDoubleText = Pattern.compile("((\\d*)|(\\d+\\.\\d*))");

        TextFormatter<Double> textFormatter = new TextFormatter<>(
                new DoubleStringConverter(), 0.0,
                change -> {
                    if (change.getControlNewText().isEmpty()) return null;
                    String newText = change.getControlNewText() ;
                    if (validDoubleText.matcher(newText).matches()) {
                        return change ;
                    } else return null ;
                });
        return textFormatter;
    }
}
