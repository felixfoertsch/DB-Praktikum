CREATE TABLE klausur (
    id                 SERIAL PRIMARY KEY,
    spezialvorlesungId INTEGER,
    grundvorlesungId   INTEGER,
    datum              DATE,
    uhrzeitVon         TIME,
    gesamtpunktzahl    INTEGER
);

CREATE TABLE abschlussklausur (
    klausurId INTEGER REFERENCES klausur PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE zwischenklausur (
    klausurId INTEGER REFERENCES klausur PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE wiederholungsklausur (
    klausurId INTEGER REFERENCES abschlussklausur PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE veranstaltung (
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100),
    jahr          DATE,
    semester      INTEGER,
    maxTeilnehmer INTEGER
);

CREATE TABLE seminar (
    veranstaltungId INTEGER REFERENCES veranstaltung PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE oberseminar (
    seminarId INTEGER REFERENCES seminar PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE problemseminar (
    seminarId INTEGER REFERENCES seminar PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE praktikum (
    veranstaltungId INTEGER REFERENCES veranstaltung PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE grundvorlesung (
    veranstaltungId INTEGER REFERENCES veranstaltung PRIMARY KEY ON DELETE CASCADE

);
CREATE TABLE spezialvorlesung (
    veranstaltungId INTEGER REFERENCES veranstaltung PRIMARY KEY ON DELETE CASCADE
);

CREATE TABLE uebung (
    veranstaltungId  INTEGER REFERENCES veranstaltung PRIMARY KEY ON DELETE CASCADE,
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
    maxPunkte INTEGER
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
    raumId          INTEGER REFERENCES raum          NOT NULL ON DELETE RESTRICT,
    veranstaltungId INTEGER REFERENCES veranstaltung NOT NULL ON DELETE CASCADE,
    wochentag       VARCHAR(100),
    zeit            TIME,
    PRIMARY KEY (raumId, veranstaltungId)
);

CREATE TABLE aufsicht (
    klausurId     INTEGER REFERENCES klausur     NOT NULL ON DELETE CASCADE,
    mitarbeiterId INTEGER REFERENCES mitarbeiter NOT NULL ON DELETE CASCADE,
    PRIMARY KEY (klausurId, mitarbeiterId)
);

CREATE TABLE bearbeitung (
    studentId INTEGER REFERENCES student NOT NULL ON DELETE CASCADE,
    aufgabeId INTEGER REFERENCES aufgabe NOT NULL ON DELETE CASCADE,
    punkte    INTEGER,
    PRIMARY KEY (studentId, aufgabeId)
);

CREATE TABLE betreut (
    mitarbeiterId   INTEGER REFERENCES mitarbeiter   NOT NULL ON DELETE CASCADE,
    veranstaltungId INTEGER REFERENCES veranstaltung NOT NULL ON DELETE CASCADE,
    PRIMARY KEY (mitarbeiterId, veranstaltungId)
);

CREATE TABLE ort (
    klausurId INTEGER REFERENCES klausur NOT NULL ON DELETE CASCADE,
    raumId    INTEGER REFERENCES raum    NOT NULL ON DELETE CASCADE,
    PRIMARY KEY (klausurId, raumId)
);

CREATE TABLE studium (
    studiengangId INTEGER REFERENCES studiengang NOT NULL ON DELETE RESTRICT,
    studentId     INTEGER REFERENCES student     NOT NULL ON DELETE CASCADE,
    imma          DATE,
    exma          DATE,
    semester      INTEGER,
    PRIMARY KEY (studiengangId, studentId)
);

CREATE TABLE studentTeilnahmeKlausur (
    studentId    INTEGER REFERENCES student NOT NULL ON DELETE CASCADE,
    klausurId    INTEGER REFERENCES klausur NOT NULL ON DELETE RESTRICT,
    erschienen   BOOLEAN,
    entschuldigt BOOLEAN,
    punkte       INTEGER,
    note         INTEGER,
    PRIMARY KEY (studentId, klausurId)
);

CREATE TABLE studentTeilnahmeVeranstaltung (
    studentId       INTEGER REFERENCES student       NOT NULL ON DELETE CASCADE,
    veranstaltungId INTEGER REFERENCES veranstaltung NOT NULL ON DELETE RESTRICT,
    note            INTEGER,
    PRIMARY KEY (studentId, veranstaltungId)
);


