import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AppController {
    public Button importButton = null;

    @FXML
    public void importButtonClicked(Event e) {
        System.out.println("Button clicked");
        FileChooser fileChooser = new FileChooser();

        Stage newStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(newStage);
        importCSV(selectedFile);
    }

    private void importCSV(File csv) {
        switch (csv.getName()) {
            case "klausuren.csv":
                System.out.println(csv.getName());
                return;
            case "klausur_aufgaben.csv":
                return;
            case "klausurerg.csv":
                return;
            case "semprakerg.csv":
                return;
            case "staff.csv":
                return;
            case "student.csv":
                return;
            case "veranstaltungen.csv":
                return;

        }
    }
}
