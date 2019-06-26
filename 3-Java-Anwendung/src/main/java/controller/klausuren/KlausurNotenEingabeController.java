package controller.klausuren;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import model.person.Student;
import model.relationen.KlausurTeilnahme;
import services.HibernateService;

import java.util.Optional;

public class KlausurNotenEingabeController {
    private HibernateService hibernateService;
    private Klausur selectedKlausur;

    @FXML
    BorderPane klausurNotenEingabeBorderPane;
    @FXML
    GridPane klausurNotenEingabeSucheGridPane;
    @FXML
    TextField matrNrSucheTextField;
    @FXML
    GridPane klausurNotenEingabeGridPane;
    @FXML
    Label klausurNotenEingabeVorname;
    @FXML
    Label klausurNotenEingabeNachname;
    @FXML
    Label klausurNotenEingabeMatrNr;
    @FXML
    Label klausurNotenEingabeInfo;

    public void setupController(HibernateService hibernateService, Klausur klausur){
        this.hibernateService = hibernateService;
        this.selectedKlausur = klausur;

        klausurNotenEingabeVorname.setText("");
        klausurNotenEingabeNachname.setText("");
        klausurNotenEingabeMatrNr.setText("");
        klausurNotenEingabeInfo.setText("");
        klausurNotenEingabeBorderPane.setBottom(null);

        // Make TextField numeric only
        matrNrSucheTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                matrNrSucheTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void suchenButtonPressed() {
        if (matrNrSucheTextField.getText().isEmpty()) {
            return;
        }
        Optional<Student> optionalStudent = hibernateService.fetchStudentByMatrNr(matrNrSucheTextField.getText());
        if (optionalStudent.isEmpty()) {
            System.out.println("No Student with this MatrNr");
            return;
        }

        Student selectedStudent = optionalStudent.get();

        klausurNotenEingabeVorname.setText(selectedStudent.getVorname());
        klausurNotenEingabeNachname.setText(selectedStudent.getNachname());
        klausurNotenEingabeMatrNr.setText(selectedStudent.getMatrikelNr());

        KlausurTeilnahme kt = hibernateService.fetchKlausurTeilnahme(selectedStudent, selectedKlausur);
        if (kt == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurTeilnahmeEingabe.fxml"));
                GridPane gridPane = loader.load();

                KlausurNotenEingabeAufgabenController c = loader.getController();
                c.setupController(hibernateService, selectedStudent, selectedKlausur);

                klausurNotenEingabeBorderPane.setBottom(gridPane);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
