CREATE TABLE studium
(
    studiengangId INTEGER REFERENCES studiengang ON DELETE RESTRICT NOT NULL,
    studentId     INTEGER REFERENCES student ON DELETE CASCADE      NOT NULL,
    imma          DATE,
    exma          DATE,
    semester      INTEGER,
    PRIMARY KEY (studiengangId, studentId)
);

CREATE TABLE studentTeilnahmeKlausur
(
    studentId    INTEGER REFERENCES student ON DELETE CASCADE  NOT NULL,
    klausurId    INTEGER REFERENCES klausur ON DELETE RESTRICT NOT NULL,
    erschienen   BOOLEAN,
    entschuldigt BOOLEAN,
    punkte       NUMERIC(3, 1),
    note         NUMERIC(2, 1),
    PRIMARY KEY (studentId, klausurId)
);

CREATE TABLE studentTeilnahmeVeranstaltung
(
    studentId       INTEGER REFERENCES student ON DELETE CASCADE        NOT NULL,
    veranstaltungId INTEGER REFERENCES veranstaltung ON DELETE RESTRICT NOT NULL,
    note            NUMERIC(2, 1),
    PRIMARY KEY (studentId, veranstaltungId)
);