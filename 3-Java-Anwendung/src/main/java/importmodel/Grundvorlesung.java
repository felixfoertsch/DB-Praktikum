package importmodel;

import java.util.HashMap;
import java.util.Map;

public class Grundvorlesung extends Veranstaltung {
    private Integer veranstaltungId;
    private Map<String, Uebung> uebungen;

    public Grundvorlesung() {
        this.uebungen = new HashMap<>();
    }

    public Map<String, Uebung> getUebungen() {
        return uebungen;
    }
}
