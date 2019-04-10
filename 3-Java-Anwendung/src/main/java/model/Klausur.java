package model;

import java.sql.Date;
import java.sql.Time;

public class Klausur {
    Integer id;
    Integer spezialvorlesungId;
    Integer grundvorlesungId;
    Date datum;
    Time uhrzeitVon;
    Integer gesamtpunktzahl;
}
