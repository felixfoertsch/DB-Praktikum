import controller.VeranstaltungController;
import dataimport.ImporterImpl;
import dataimport.model.Universitaet;
import javafx.event.Event;
import javafx.fxml.FXML;
import model.Studiengang;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppController {
    private SessionFactory sessionFactory;

    public AppController() {
        try {
            setUpHibernate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void importButtonClicked(Event e) {
        var importer = new ImporterImpl();
        var universitaet = new Universitaet();
        try {
            var files = importer.importCSVtoMemory();
            universitaet = importer.parseCSVandCreateModel(files);
            importer.checkMultiplicities(universitaet);
            importer.persistModel(universitaet);
        } catch (NullPointerException npe) {
            System.out.println("Import canceled.");
        }
    }

    @FXML
    public void newVeranstaltungButtonClicked(Event e) {
        VeranstaltungController vc = new VeranstaltungController();
        vc.hello();
    }

    @FXML
    public void newKlausurButtonClicked(Event e) {
    }

    @FXML
    public void testStudiengangInsert() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        var studiengang = new Studiengang();
        studiengang.setName("Test");
        studiengang.setAbschluss("Testabschluss");
        studiengang.setRegelstudienzeit(111);

        session.save(studiengang);
        session.getTransaction().commit();
        session.close();
    }


    @FXML
    protected void setUpHibernate() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @FXML
    protected void tearDownHibernate() throws Exception {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

}
