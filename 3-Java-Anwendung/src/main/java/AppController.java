import controller.VeranstaltungController;
import dataimport.Importer;
import dataimport.ImporterImpl;
import dataimport.model.Universitaet;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.File;
import java.util.Map;

public class AppController {
    public Button importButton = null;
    private Importer importer;
    private Universitaet universitaet;
    private SessionFactory sessionFactory;

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

    @FXML
    protected void setUpHibernate() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @FXML
    protected void tearDownHibernate() throws Exception {
        if ( sessionFactory != null ) {
            sessionFactory.close();
        }
    }

}
