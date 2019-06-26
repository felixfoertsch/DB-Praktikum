package controller.klausuren;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PropertySheet;
import org.controlsfx.property.BeanPropertyUtils;
import services.HibernateService;

import java.time.LocalDate;
import java.time.LocalTime;

public class KlausurenController {

    @FXML
    MasterDetailPane klausurenMasterDetailPane;
    // Master
    @FXML
    TableView<Klausur> klausurTableView;
    @FXML
    TableColumn<Klausur, Integer> id;
    @FXML
    TableColumn<Klausur, String> va;
    @FXML
    TableColumn<Klausur, LocalDate> date;
    @FXML
    TableColumn<Klausur, LocalTime> time;
    @FXML
    TableColumn<Klausur, Double> points;

    // Detail
    @FXML
    TabPane klausurTabPane;
    @FXML
    Tab klausurPropertySheet;
    @FXML
    Tab klausurStatistikTab;
    @FXML
    Tab klausurTeilnehmerTab;
    @FXML
    Tab klausurTeilnehmerAbwesendTab;
    @FXML
    Tab klausurNotenEingabeTab;
    @FXML
    Tab klausurNotenVerteilungTab;

    private HibernateService hibernateService;

    public KlausurenController() {

    }

    public void setupController(HibernateService hibernateService) {
        this.hibernateService = hibernateService;
        configureMaster();
    }

    private void configureMaster() {
        ObservableList<Klausur> klausuren = FXCollections.observableArrayList(hibernateService.fetchKlausuren());
        klausurTableView.setItems(klausuren);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        date.setCellValueFactory(new PropertyValueFactory<>("datum"));
        time.setCellValueFactory(new PropertyValueFactory<>("uhrzeitVon"));
        points.setCellValueFactory(new PropertyValueFactory<>("gesamtpunktzahl"));

        va.setCellValueFactory(klausurStringCellDataFeatures -> {
            if (klausurStringCellDataFeatures.getValue() != null && klausurStringCellDataFeatures.getValue().getGrundvorlesung() != null) {
                return new SimpleStringProperty(klausurStringCellDataFeatures.getValue().getGrundvorlesung().getName());
            } else if (klausurStringCellDataFeatures.getValue() != null && klausurStringCellDataFeatures.getValue().getSpezialvorlesung() != null) {
                return new SimpleStringProperty(klausurStringCellDataFeatures.getValue().getSpezialvorlesung().getName());
            } else {
                return new SimpleStringProperty("<Keine zugehörige VA>");
            }
        });
        klausurTableView.getSortOrder().add(date);

        // Add a listener for the selected row
        klausurTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                configureDetail(newSelection);
            }
        });
    }

    @FXML
    private void configureDetail(Klausur klausur) {
        Klausur k = hibernateService.fetchKlausurById(klausur.getId());
        setupPropertyTab(k);
        setupStatistikTab(k);
        setupTeilnehmerTab(k);
        setupAbwesendTab(k);
        setupNotenEingabeTab(k);
        setupNotenVerteilungTab(k);
        klausurenMasterDetailPane.showDetailNodeProperty().setValue(true);
    }

    private void setupPropertyTab(Klausur klausur) {
        PropertySheet propertySheet = new PropertySheet(BeanPropertyUtils.getProperties(klausur));
        klausurPropertySheet.setContent(propertySheet);
    }

    private void setupStatistikTab(Klausur klausur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurStatistik.fxml"));
            GridPane gridPane = loader.load();
            KlausurStatistikController c = loader.getController();
            c.setupController(klausur);
            klausurStatistikTab.setContent(gridPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupTeilnehmerTab(Klausur klausur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurTeilnehmer.fxml"));
            TableView tableView = loader.load();
            KlausurTeilnehmerController c = loader.getController();
            c.setupController(klausur);
            klausurTeilnehmerTab.setContent(tableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupAbwesendTab(Klausur klausur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurTeilnehmerAbwesend.fxml"));
            TableView tableView = loader.load();
            KlausurTeilnehmerAbwesendController c = loader.getController();
            c.setupController(klausur);
            klausurTeilnehmerAbwesendTab.setContent(tableView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupNotenEingabeTab(Klausur klausur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurNotenEingabe.fxml"));
            BorderPane borderPane = loader.load();
            KlausurNotenEingabeController c = loader.getController();
            c.setupController(hibernateService, klausur);
            klausurNotenEingabeTab.setContent(borderPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupNotenVerteilungTab(Klausur klausur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/KlausurNotenVerteilung.fxml"));
            GridPane gridPane = loader.load();
            KlausurNotenVerteilungController c = loader.getController();
            c.setupController(hibernateService, klausur);
            klausurNotenVerteilungTab.setContent(gridPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
