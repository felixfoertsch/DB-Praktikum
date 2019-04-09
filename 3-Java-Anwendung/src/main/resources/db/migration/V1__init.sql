CREATE TABLE klausur
(
    id                 SERIAL PRIMARY KEY,
    spezialvorlesungId INTEGER,
    grundvorlesungId   INTEGER,
    datum              DATE,
    uhrzeitVon         TIME,
    gesamtpunktzahl    INTEGER
);

CREATE TABLE abschlussklausur
(
    id INTEGER REFERENCES klausur PRIMARY KEY
);

CREATE TABLE zwischenklausur
(
    id INTEGER REFERENCES klausur PRIMARY KEY
);

CREATE TABLE wiederholungsklausur
(
    id INTEGER REFERENCES abschlussklausur PRIMARY KEY
);

CREATE TABLE veranstaltung
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(100),
    jahr          DATE,
    semester      INTEGER,
    maxTeilnehmer INTEGER
);

CREATE TABLE seminar
(
    id INTEGER REFERENCES veranstaltung PRIMARY KEY
);

CREATE TABLE oberseminar
(
    id INTEGER REFERENCES seminar PRIMARY KEY
);

CREATE TABLE problemseminar
(
    id INTEGER REFERENCES seminar PRIMARY KEY
);

CREATE TABLE praktikum
(
    id INTEGER REFERENCES veranstaltung PRIMARY KEY
);

CREATE TABLE grundvorlesung
(
    id INTEGER REFERENCES veranstaltung PRIMARY KEY

);
CREATE TABLE spezialvorlesung
(
    id INTEGER REFERENCES veranstaltung PRIMARY KEY
);

CREATE TABLE uebung
(
    id               INTEGER REFERENCES veranstaltung PRIMARY KEY,
    grundvorlesungId INTEGER REFERENCES grundvorlesung
);

CREATE TABLE mitarbeiter
(
    id       SERIAL PRIMARY KEY,
    vorname  VARCHAR(100),
    nachname VARCHAR(100),
    email    VARCHAR(100)
);

CREATE TABLE student
(
    id         SERIAL PRIMARY KEY,
    matrikelNr VARCHAR(100),
    vorname    VARCHAR(100),
    nachname   VARCHAR(100),
    uniMail    VARCHAR(100)
);

CREATE TABLE aufgabe
(
    id        SERIAL PRIMARY KEY,
    klausurId INTEGER REFERENCES klausur,
    rang      INTEGER,
    maxPunkte INTEGER
);

CREATE TABLE raum
(
    id            SERIAL PRIMARY KEY,
    mitarbeiterId INTEGER REFERENCES mitarbeiter,
    bezeichnung   VARCHAR(100)
);

CREATE TABLE studiengang
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(100),
    abschluss        VARCHAR(100),
    regelstudienzeit INTEGER
);

CREATE TABLE abhaltung
(
    raumId          INTEGER REFERENCES raum NOT NULL,
    veranstaltungId INTEGER REFERENCES veranstaltung NOT NULL,
    wochentag       VARCHAR(100),
    zeit            TIME,
    PRIMARY KEY (raumId, veranstaltungId)
);

CREATE TABLE aufsicht
(
    klausurId     INTEGER REFERENCES klausur NOT NULL,
    mitarbeiterId INTEGER REFERENCES mitarbeiter NOT NULL,
    PRIMARY KEY (klausurId, mitarbeiterId)
);

CREATE TABLE bearbeitung
(
    studentId INTEGER REFERENCES student NOT NULL,
    aufgabeId INTEGER REFERENCES aufgabe NOT NULL,
    punkte    INTEGER,
    PRIMARY KEY (studentId, aufgabeId)
);

CREATE TABLE betreut
(
    mitarbeiterId   INTEGER REFERENCES mitarbeiter NOT NULL,
    veranstaltungId INTEGER REFERENCES veranstaltung NOT NULL,
    PRIMARY KEY (mitarbeiterId, veranstaltungId)
);

CREATE TABLE ort
(
    klausurId INTEGER REFERENCES klausur NOT NULL,
    raumId  INTEGER REFERENCES raum NOT NULL,
    PRIMARY KEY (klausurId, raumId)
);

CREATE TABLE studium
(
    studiengangId INTEGER REFERENCES studiengang NOT NULL,
    studentId     INTEGER REFERENCES student NOT NULL,
    imma          DATE,
    exma          DATE,
    semester      INTEGER,
    PRIMARY KEY (studiengangId, studentId)
);

CREATE TABLE studentTeilnahmeKlausur
(
    studentId    INTEGER REFERENCES student NOT NULL,
    klausurId    INTEGER REFERENCES klausur NOT NULL,
    erschienen   BOOLEAN,
    entschuldigt BOOLEAN,
    punkte       INTEGER,
    note         INTEGER,
    PRIMARY KEY (studentId, klausurId)
);

CREATE TABLE studentTeilnahmeVeranstaltung
(
    studentId       INTEGER REFERENCES student NOT NULL ,
    veranstaltungId INTEGER REFERENCES veranstaltung NOT NULL ,
    note            INTEGER,
    PRIMARY KEY (studentId, veranstaltungId)
);


