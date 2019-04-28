import javax.sql.DataSource;
import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface Importer {
    Map<String, File> importCSVtoMemory();
    void parseCSVandImportToDataSource(Map<String, File> csv, DataSource dataSource);
}
