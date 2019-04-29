import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.Universitaet;

import javax.sql.DataSource;
import java.io.File;
import java.util.Collection;
import java.util.Map;

public class AppController {
    public Button importButton = null;
    private ImporterImpl importer;
    private Universitaet universitaet;

    public AppController() {
        this.importer = new ImporterImpl();
        this.universitaet = new Universitaet();
    }

    @FXML
    public void importButtonClicked(Event e) {
        Map<String, File> files = importer.importCSVtoMemory();
        Universitaet universitaet = importer.parseCSVandCreateModel(files);
        //TODO: Get following out of Controller
        PersisterImpl persister = new PersisterImpl();
        persister.persistUniversitaet(universitaet);
    }

}
