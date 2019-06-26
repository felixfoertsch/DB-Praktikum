package controller.klausuren;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import model.klausur.Klausur;
import services.HibernateService;


public class KlausurNotenVerteilungController {
    private HibernateService hibernateService;

    @FXML
    private GridPane klausurNotenVerteilungGridPane;

    public void setupController(HibernateService hibernateService, Klausur klausur) {
        this.hibernateService = hibernateService;
        decodeNotenschluesselString(klausur.getNotenschluessel());
    }

    private int[] decodeNotenschluesselString(String notenschluesselString) {
        String[] split = notenschluesselString.split(",");
        int[] notenschluesselArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < 11; i++) {
            notenschluesselArray[i] = Integer.valueOf(split[i]);
        }
        return notenschluesselArray;
    }

}
