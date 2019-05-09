import model.Universitaet;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface Importer {
    Map<String, File> importCSVtoMemory();
    Universitaet parseCSVandCreateModel(Map<String, File> csv);
    void persistModel(Universitaet universitaet);
}
