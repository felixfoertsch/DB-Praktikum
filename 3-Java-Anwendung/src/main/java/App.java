import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Create a FMXLLoader to load the main window and assign a custom
        // controller class to it, that will handle the events.
        var loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/Main.fxml"));

        // After the loader is set up, the window can now be loaded in and shown.
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

}