package model;

public class Uebung extends Veranstaltung {
    Integer veranstaltungId;
    Integer grundvorlesungId;

    @Override
    public String generateKey() {
        return super.getKennung() + "_" + extractGroupID();
    }

    private String extractGroupID() {
        StringBuilder name = new StringBuilder(super.getName()).reverse();
        return Character.toString(name.charAt(0)).toLowerCase();
    }
}
