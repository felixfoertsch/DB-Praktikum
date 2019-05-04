import config.HibernateConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import manager.StudentManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Create SessionFactory
        SessionFactory sessionFactory = HibernateConfig.createSessionFactory();

        // Create a FMXLLoader to load the main window and assign a custom
        // controller class to it, that will handle the events.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ui/Main.fxml"));

        // After the loader is set up, the window can now be loaded in and shown.
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}