import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.sql.DataSource;
import java.io.File;
import java.util.Collection;
import java.util.Map;

public class AppController {
    public Button importButton = null;
    private Importer importer;
    private DataSource dataSource;

    public AppController() {
        this.importer = new ImporterImpl();
    }

    @FXML
    public void importButtonClicked(Event e) {
        Map<String, File> files = importer.importCSVtoMemory();
        importer.parseCSVandImportToDataSource(files, dataSource);
    }

}
