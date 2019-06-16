import controller.VeranstaltungController;
import dataimport.Importer;
import dataimport.ImporterImpl;
import dataimport.model.Universitaet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.util.Map;

public class AppController {
    public Button importButton = null;
    private Importer importer;
    private Universitaet universitaet;

    public AppController() {

    }

    @FXML
    public void importButtonClicked(Event e) {
        this.importer = new ImporterImpl();
        this.universitaet = new Universitaet();
        Map<String, File> files = importer.importCSVtoMemory();
        this.universitaet = importer.parseCSVandCreateModel(files);
        importer.checkMultiplicities(this.universitaet);
        importer.persistModel(this.universitaet);
    }

    @FXML
    public void newVeranstaltungButtonClicked(Event e) {
        VeranstaltungController vc = new VeranstaltungController();
        vc.hello();
    }

}
