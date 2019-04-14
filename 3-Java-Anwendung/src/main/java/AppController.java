import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AppController {
    public Button importButton = null;

    @FXML
    public void importButtonClicked(Event e) {
        System.out.println("Button clicked");
    }
}
