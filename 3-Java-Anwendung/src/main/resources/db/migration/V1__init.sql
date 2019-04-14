CREATE TABLE klausur (
    id                 SERIAL PRIMARY KEY,
    spezialvorlesungId INTEGER,
    grundvorlesungId   INTEGER,
    datum              DATE,
    uhrzeitVon         TIME,
    gesamtpunktzahl    INTEGER
);

CREATE TABLE abschlussklausur (
    klausurId INTEGER REFERENCES klausur ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE zwischenklausur (
    klausurId INTEGER REFERENCES klausur ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE wiederholungsklausur (
    klausurId INTEGER REFERENCES abschlussklausur ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE veranstaltung (
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100),
    jahr          DATE,
    semester      VARCHAR(100),
    maxTeilnehmer INTEGER
);

CREATE TABLE seminar (
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE oberseminar (
    seminarId INTEGER REFERENCES seminar ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE problemseminar (
    seminarId INTEGER REFERENCES seminar ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE praktikum (
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE grundvorlesung (
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY

);
CREATE TABLE spezialvorlesung (
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE uebung (
    veranstaltungId  INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY,
    grundvorlesungId INTEGER REFERENCES grundvorlesung ON DELETE CASCADE
);

CREATE TABLE mitarbeiter (
    id       SERIAL PRIMARY KEY,
    vorname  VARCHAR(100),
    nachname VARCHAR(100),
    email    VARCHAR(100)
);

CREATE TABLE student (
    id         SERIAL PRIMARY KEY,
    matrikelNr VARCHAR(100),
    vorname    VARCHAR(100),
    nachname   VARCHAR(100),
    uniMail    VARCHAR(100)
);

CREATE TABLE aufgabe (
    id        SERIAL PRIMARY KEY,
    klausurId INTEGER REFERENCES klausur ON DELETE CASCADE,
    rang      INTEGER,
    maxPunkte NUMERIC(2, 1)
);

CREATE TABLE raum (
    id            SERIAL PRIMARY KEY,
    mitarbeiterId INTEGER REFERENCES mitarbeiter ON DELETE SET NULL,
    bezeichnung   VARCHAR(100)
);

CREATE TABLE studiengang (
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(100),
    abschluss        VARCHAR(100),
    regelstudienzeit INTEGER
);

CREATE TABLE abhaltung (
    raumId          INTEGER REFERENCES raum          ON DELETE RESTRICT NOT NULL,
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE CASCADE NOT NULL,
    wochentag       VARCHAR(100),
    zeit            TIME,
    PRIMARY KEY (raumId, veranstaltungId)
);

CREATE TABLE aufsicht (
    klausurId     INTEGER REFERENCES klausur     ON DELETE CASCADE NOT NULL,
    mitarbeiterId INTEGER REFERENCES mitarbeiter ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (klausurId, mitarbeiterId)
);

CREATE TABLE bearbeitung (
    studentId INTEGER REFERENCES student ON DELETE CASCADE NOT NULL,
    aufgabeId INTEGER REFERENCES aufgabe ON DELETE CASCADE NOT NULL,
    punkte    NUMERIC(2, 1),
    PRIMARY KEY (studentId, aufgabeId)
);

CREATE TABLE betreut (
    mitarbeiterId   INTEGER REFERENCES mitarbeiter   ON DELETE CASCADE NOT NULL,
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (mitarbeiterId, veranstaltungId)
);

CREATE TABLE ort (
    klausurId INTEGER REFERENCES klausur ON DELETE CASCADE NOT NULL,
    raumId    INTEGER REFERENCES raum    ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (klausurId, raumId)
);

CREATE TABLE studium (
    studiengangId INTEGER REFERENCES studiengang ON DELETE RESTRICT NOT NULL,
    studentId     INTEGER REFERENCES student     ON DELETE CASCADE NOT NULL,
    imma          DATE,
    exma          DATE,
    semester      INTEGER,
    PRIMARY KEY (studiengangId, studentId)
);

CREATE TABLE studentTeilnahmeKlausur (
    studentId    INTEGER REFERENCES student ON DELETE CASCADE NOT NULL,
    klausurId    INTEGER REFERENCES klausur ON DELETE RESTRICT NOT NULL,
    erschienen   BOOLEAN,
    entschuldigt BOOLEAN,
    punkte       NUMERIC(2, 1),
    note         NUMERIC(2, 1),
    PRIMARY KEY (studentId, klausurId)
);

CREATE TABLE studentTeilnahmeVeranstaltung (
    studentId       INTEGER REFERENCES student       ON DELETE CASCADE NOT NULL,
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE RESTRICT NOT NULL,
    note            NUMERIC(2, 1),
    PRIMARY KEY (studentId, veranstaltungId)
);


