CREATE TABLE klausur
(
    id                  SERIAL PRIMARY KEY,
    spezialvorlesung_id INTEGER,
    grundvorlesung_id   INTEGER,
    datum               DATE,
    uhrzeitvon          TIME,
    gesamtpunktzahl     NUMERIC(3, 1)
);

CREATE TABLE abschlussklausur
(
    klausur_id INTEGER REFERENCES klausur ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE zwischenklausur
(
    klausur_id INTEGER REFERENCES klausur ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE wiederholungsklausur
(
    klausur_id INTEGER REFERENCES abschlussklausur ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE veranstaltung
(
    id            SERIAL PRIMARY KEY,
    name          TEXT,
    jahr          INTEGER,
    semester      TEXT,
    maxteilnehmer INTEGER
);

CREATE TABLE seminar
(
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE oberseminar
(
    veranstaltung_id INTEGER REFERENCES seminar ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE problemseminar
(
    veranstaltung_id INTEGER REFERENCES seminar ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE praktikum
(
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE grundvorlesung
(
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY

);

CREATE TABLE spezialvorlesung
(
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY
);

CREATE TABLE uebung
(
    veranstaltung_id  INTEGER REFERENCES veranstaltung ON DELETE CASCADE PRIMARY KEY,
    grundvorlesung_id INTEGER REFERENCES grundvorlesung ON DELETE CASCADE
);

CREATE TABLE raum
(
    id          SERIAL PRIMARY KEY,
    bezeichnung TEXT UNIQUE NOT NULL
);

CREATE TABLE mitarbeiter
(
    id       SERIAL PRIMARY KEY,
    vorname  TEXT,
    nachname TEXT,
    email    TEXT,
    raum_id  INTEGER REFERENCES raum
);

CREATE TABLE student
(
    id         SERIAL PRIMARY KEY,
    matrikelnr TEXT,
    vorname    TEXT,
    nachname   TEXT,
    unimail    TEXT
);

CREATE TABLE aufgabe
(
    id         SERIAL PRIMARY KEY,
    klausur_id INTEGER REFERENCES klausur ON DELETE CASCADE,
    rang       INTEGER,
    maxpunkte  NUMERIC(3, 1)
);

CREATE TABLE studiengang
(
    id               SERIAL PRIMARY KEY,
    name             TEXT,
    abschluss        TEXT,
    regelstudienzeit INTEGER
);

CREATE TABLE abhaltung
(
    raum_id          INTEGER REFERENCES raum ON DELETE RESTRICT         NOT NULL,
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE CASCADE NOT NULL,
    wochentag        TEXT,
    zeit             TIME,
    PRIMARY KEY (raum_id, veranstaltung_id)
);

CREATE TABLE aufsicht
(
    klausur_id     INTEGER REFERENCES klausur ON DELETE CASCADE     NOT NULL,
    mitarbeiter_id INTEGER REFERENCES mitarbeiter ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (klausur_id, mitarbeiter_id)
);

CREATE TABLE bearbeitung
(
    student_id INTEGER REFERENCES student ON DELETE CASCADE NOT NULL,
    aufgabe_id INTEGER REFERENCES aufgabe ON DELETE CASCADE NOT NULL,
    punkte     NUMERIC(3, 1),
    PRIMARY KEY (student_id, aufgabe_id)
);

CREATE TABLE betreut
(
    mitarbeiter_id   INTEGER REFERENCES mitarbeiter ON DELETE CASCADE   NOT NULL,
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE CASCADE NOT NULL,
    PRIMARY KEY (mitarbeiter_id, veranstaltung_id)
);

CREATE TABLE ort
(
    klausur_id INTEGER REFERENCES klausur ON DELETE CASCADE NOT NULL,
    raum_id    INTEGER REFERENCES raum ON DELETE CASCADE    NOT NULL,
    PRIMARY KEY (klausur_id, raum_id)
);

CREATE TABLE studium
(
    studiengang_id INTEGER REFERENCES studiengang ON DELETE RESTRICT NOT NULL,
    student_id     INTEGER REFERENCES student ON DELETE CASCADE      NOT NULL,
    imma           INTEGER,
    exma           INTEGER,
    semester       INTEGER,
    PRIMARY KEY (studiengang_id, student_id)
);

CREATE TABLE studentteilnahmeklausur
(
    student_id   INTEGER REFERENCES student ON DELETE CASCADE  NOT NULL,
    klausur_id   INTEGER REFERENCES klausur ON DELETE RESTRICT NOT NULL,
    erschienen   BOOLEAN,
    entschuldigt BOOLEAN,
    punkte       NUMERIC(3, 1),
    note         NUMERIC(2, 1),
    PRIMARY KEY (student_id, klausur_id)
);

CREATE TABLE studentteilnahmeveranstaltung
(
    student_id       INTEGER REFERENCES student ON DELETE CASCADE        NOT NULL,
    veranstaltung_id INTEGER REFERENCES veranstaltung ON DELETE RESTRICT NOT NULL,
    note             NUMERIC(2, 1),
    PRIMARY KEY (student_id, veranstaltung_id)
);