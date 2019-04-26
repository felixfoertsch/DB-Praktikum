import model.Universitaet;

import java.io.File;
import java.util.Collection;

public interface Importer {
    Collection<File> importCSVtoMemory();
    Universitaet parseCSVandCreateModel(Collection<File> csv);
}
