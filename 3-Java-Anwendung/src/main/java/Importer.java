import javax.sql.DataSource;
import java.io.File;
import java.util.Collection;

public interface Importer {
    Collection<File> importCSVtoMemory();
    void parseCSVandImportToDataSource(Collection<File> csv, DataSource dataSource);
}
