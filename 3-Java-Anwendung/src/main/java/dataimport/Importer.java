package dataimport;

import dataimport.model.Universitaet;

import java.io.File;
import java.util.Map;

public interface Importer {
    Map<String, File> importCSVtoMemory();

    Universitaet parseCSVandCreateModel(Map<String, File> csv);

    void checkMultiplicities(Universitaet universitaet);

    void persistModel(Universitaet universitaet);
}
