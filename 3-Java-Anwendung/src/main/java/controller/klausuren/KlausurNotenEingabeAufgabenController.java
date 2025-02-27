package controller.klausuren;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;
import model.Aufgabe;
import model.klausur.Klausur;
import model.person.Student;
import model.relationen.AufgabenBearbeitung;
import model.relationen.KlausurTeilnahme;
import services.HibernateService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class KlausurNotenEingabeAufgabenController {
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
    private HibernateService hibernateService;
    private Student selectedStudent;
    private Klausur selectedKlausur;
    private List<Aufgabe> aufgaben;
    private Map<Integer, TextField> aufgabenMap = new HashMap<>();
    private Label punkteSummeLabel = new Label("");
    private Double gesamtpunkte = 0.0;

    public void setupController(HibernateService hibernateService, Student student, Klausur klausur) {
        this.hibernateService = hibernateService;
        this.selectedStudent = student;
        this.selectedKlausur = klausur;
        this.aufgaben = klausur.getAufgaben();

        if (aufgaben.isEmpty()) {
            Label keineAufgaben = new Label("Diese Klausur enthält keine Aufgaben.");
            klausurTeilnahmeEingabeGridPane.getChildren().remove(hinzufuegenButton);
            klausurTeilnahmeEingabeGridPane.add(keineAufgaben, 0, 5);
            klausurTeilnahmeEingabeGridPane.add(hinzufuegenButton, 0, 6);
        } else {
            klausurNotenEingabeNotenTextField.setTextFormatter(getDoubleFormatterWithMaxValue(5.0));
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
                tempTextField.setTextFormatter(getDoubleFormatterWithMaxValue(a.getMaxPunkte()));
                aufgabenMap.put(a.getRang(), tempTextField);

                klausurTeilnahmeEingabeGridPane.add(tempLabel, 0, rowcounter);
                klausurTeilnahmeEingabeGridPane.add(tempTextField, 1, rowcounter);
                klausurTeilnahmeEingabeGridPane.add(new Label("/ " + a.getMaxPunkte().toString()), 2, rowcounter);
                rowcounter++;
            }

            klausurTeilnahmeEingabeGridPane.add(klausurNotenEingabePunkteLabel, 0, rowcounter);
            klausurTeilnahmeEingabeGridPane.add(punkteSummeLabel, 1, rowcounter);
            klausurTeilnahmeEingabeGridPane.add(new Label("/ " + selectedKlausur.getGesamtpunktzahl().toString()), 2, rowcounter);
            klausurTeilnahmeEingabeGridPane.add(hinzufuegenButton, 0, rowcounter + 1);
        }
    }

    @FXML
    private void hinzufuegenButtonPressed() {
        if (gesamtpunkte > selectedKlausur.getGesamtpunktzahl()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Fehler");
            a.setHeaderText("Punktzahl überprüfen!");
            a.setResizable(false);
            a.setContentText("Punktzahl überschreitet Gesamtpunkzahl.");
            a.showAndWait();
            return;
        }
        if (klausurNotenEingabeNotenTextField.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Fehler");
            a.setHeaderText("Note überprüfen!");
            a.setResizable(false);
            a.setContentText("Die eingegebene Note ist ungültig.");
            a.showAndWait();
            return;
        }

        KlausurTeilnahme kt = new KlausurTeilnahme();
        kt.setEntschuldigt(klausurNotenEingabeEntschuldigtCheckBox.isSelected());
        kt.setErschienen(klausurNotenEingabeErschienenCheckBox.isSelected());
        kt.setKlausur(selectedKlausur);
        kt.setStudent(selectedStudent);
        kt.setPunkte(gesamtpunkte);
        kt.setNote(Double.valueOf(klausurNotenEingabeNotenTextField.getText()));

        hibernateService.saveKlausurTeilnahme(kt);

        for (Aufgabe a : aufgaben) {
            String aufgabenPunkte = aufgabenMap.get(a.getRang()).getText();
            AufgabenBearbeitung ab = new AufgabenBearbeitung(selectedStudent, a, Double.valueOf(aufgabenPunkte));
            hibernateService.saveAufgabenBearbeitung(ab);
        }
    }

    private TextFormatter<Double> getDoubleFormatterWithMaxValue(Double maxPunkte) {
        Pattern validDoubleText = Pattern.compile("((\\d*)|(\\d+\\.\\d*))");
        return new TextFormatter<>(
                new DoubleStringConverter(), 0.0,
                change -> {
                    if (change.getControlNewText().isEmpty()) return null;
                    String newText = change.getControlNewText();
                    if (validDoubleText.matcher(newText).matches()) {
                        if (Double.valueOf(change.getControlNewText()) > maxPunkte) return null;
                        return change;
                    } else return null;
                });
    }
}
