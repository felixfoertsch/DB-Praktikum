package controller.klausuren;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.converter.IntegerStringConverter;
import model.klausur.Klausur;
import services.HibernateService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class KlausurNotenVerteilungController {
    private String[] noten = {"1,0", "1,3", "1,7", "2,0", "2,3", "2,7", "3,0", "3,3", "3,7", "4,0", "5,0"};
    private HibernateService hibernateService;
    private Klausur selectedKlausur;
    private List<TextField> schluesselTextFields = new ArrayList<>();

    @FXML
    private GridPane klausurNotenVerteilungGridPane;

    public void setupController(HibernateService hibernateService, Klausur klausur) {
        this.hibernateService = hibernateService;
        this.selectedKlausur = klausur;
        int[] notenschluessel = decodeNotenschluesselString(klausur.getNotenschluessel());
        for (int i = 0; i < notenschluessel.length; i++) {
            Label notenLabel = new Label(noten[i]);
            TextField schluesselTextField = new TextField();
            schluesselTextField.setTextFormatter(getDecimalFormatter());
            schluesselTextField.setText(String.valueOf(notenschluessel[i]));
            double punkte = klausur.getGesamtpunktzahl() * notenschluessel[i] * 0.01;
            Label punkteLabel = new Label(String.valueOf(punkte));
            klausurNotenVerteilungGridPane.add(notenLabel, 0, i + 1);
            klausurNotenVerteilungGridPane.add(schluesselTextField, 1, i + 1);
            schluesselTextFields.add(schluesselTextField);
            klausurNotenVerteilungGridPane.add(punkteLabel, 2, i + 1);
        }
    }

    @FXML
    private void eintragenButtonPressed() {
        StringBuilder stringBuilder = new StringBuilder();
        for (TextField tf : schluesselTextFields) {
            stringBuilder.append(tf.getText()).append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        String string = stringBuilder.toString();
        selectedKlausur.setNotenschluessel(string);
        hibernateService.updateKlausur(selectedKlausur);
    }

    private int[] decodeNotenschluesselString(String notenschluesselString) {
        String[] split = notenschluesselString.split(",");
        int[] notenschluesselArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 11; i++) {
            notenschluesselArray[i] = Integer.valueOf(split[i]);
        }
        return notenschluesselArray;
    }

    private TextFormatter<Integer> getDecimalFormatter() {
        Pattern validDecimalText = Pattern.compile("\\d*");
        return new TextFormatter<>(
                new IntegerStringConverter(), 0,
                change -> {
                    if (change.getControlNewText().isEmpty()) return null;
                    String newText = change.getControlNewText();
                    if (validDecimalText.matcher(newText).matches()) {
                        if (Integer.valueOf(change.getControlNewText()) > 100) return null;
                        return change;
                    } else return null;
                });
    }

}
