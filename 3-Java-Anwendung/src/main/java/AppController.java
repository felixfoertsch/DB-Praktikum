import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javax.sql.DataSource;
import java.io.File;
import java.util.Collection;

public class AppController {
    public Button importButton = null;
    private ImporterImpl importer;
    private DataSource dataSource;

    public AppController() {
        this.importer = new ImporterImpl();
    }

    @FXML
    public void importButtonClicked(Event e) {
        Collection<File> files = importer.importArrayWrappedSingleCSV();
        importer.parseCSVandImportToDataSource(files, dataSource);
    }

}
