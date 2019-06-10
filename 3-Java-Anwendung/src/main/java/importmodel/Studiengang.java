package importmodel;

public class Studiengang {
    private Integer id;
    private String name;
    private String abschluss;
    private Integer regelstudienzeit;

    public Studiengang(String name, String abschluss, String regelstudienzeit) {
        this.name = name;
        this.abschluss = abschluss;
        this.regelstudienzeit = Integer.valueOf(regelstudienzeit);
    }

    public String getName() {
        return name;
    }

    public String getAbschluss() {
        return abschluss;
    }

    public Integer getRegelstudienzeit() {
        return regelstudienzeit;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
