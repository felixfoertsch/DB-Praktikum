package dataimport.model;

public class Uebung extends Veranstaltung {
    Integer veranstaltungId;
    Integer grundvorlesungId;
    String zugehoerigeVA;

    @Override
    public String generateKey() {
        return super.generateKey() + "_" + extractGroupID();
    }

    private String extractGroupID() {
        StringBuilder name = new StringBuilder(super.getName()).reverse();
        return Character.toString(name.charAt(0)).toLowerCase();
    }

    public void setZugehoerigeVA(String zugehoerigeVA) {
        this.zugehoerigeVA = zugehoerigeVA;
    }

    public String getZugehoerigeVA() {
        return zugehoerigeVA;
    }
}
