-- 2.
-- innerhalb eines Jahres 3 Veranstaltungen zusammen besucht
CREATE OR REPLACE VIEW lernpartner AS
    WITH veranstaltungsMatching AS (-- Welche Studenten nehmen an welchen Veranstaltungen und in welchem Jahr teil?
        SELECT veranstaltung_id,
               student_id,
               jahr
        FROM veranstaltung AS v
                 JOIN studentteilnahmeveranstaltung
                      ON veranstaltung_id = v.id

        UNION

        SELECT DISTINCT veranstaltung_id,
                        studentteilnahmeklausur.student_id,
                        jahr
        FROM (
                 SELECT klausur.id                                       AS klausur_id,
                        coalesce(spezialvorlesung_id, grundvorlesung_id) AS veranstaltung_id,
                        jahr
                 FROM veranstaltung AS v
                          JOIN klausur
                               ON coalesce(spezialvorlesung_id, grundvorlesung_id) = v.id) AS k
                 JOIN studentteilnahmeklausur
                      ON studentteilnahmeklausur.klausur_id = k.klausur_id
    )
    SELECT DISTINCT a.student_id AS links,
                    b.student_id    rechts -- DISTINCT, da lernpartner-Eigenschaft in mehrere
    FROM veranstaltungsMatching AS a
             FULL JOIN veranstaltungsMatching AS b ON a.veranstaltung_id = b.veranstaltung_id
    WHERE a.student_id < b.student_id
    GROUP BY a.jahr, a.student_id, b.student_id -- a und b sind in einer Veranstaltung, und durch a.jahr im selben Jahr
    HAVING count(*) >= 3;


-- Lerpartner ersten Grades
SELECT id, concat(vorname, ' ', nachname), matrikelnr
FROM (
         SELECT rechts AS partnerId
         FROM lernpartner
                  JOIN student ON links = id
         WHERE matrikelnr = '3153305'

         UNION

         SELECT links AS partnerId
         FROM lernpartner
                  JOIN student ON rechts = id
         WHERE matrikelnr = '3153305'
     ) AS s
         JOIN student ON partnerId = id;


-- Lernpartner ersten und zweiten Grades
WITH partnerFirstDegree AS (
    SELECT rechts AS partnerId
    FROM lernpartner
             JOIN student ON links = id
    WHERE matrikelnr = '3153305'

    UNION

    SELECT links AS partnerId
    FROM lernpartner
             JOIN student ON rechts = id
    WHERE matrikelnr = '3153305'
)
SELECT DISTINCT id, concat(vorname, ' ', nachname), matrikelnr
FROM (
         SELECT rechts AS partnerSDId
         FROM partnerFirstDegree
                  JOIN lernpartner on partnerFirstDegree.partnerId = lernpartner.links

         UNION

         SELECT links AS partneSDId
         FROM partnerFirstDegree
                  JOIN lernpartner on partnerFirstDegree.partnerId = lernpartner.rechts
     ) AS s
         JOIN student ON partnerSDId = id;


-- 3.
-- Erstellen Sie einen Trigger, der Studierende, die an einer Zwischenklausur
-- erfolgreich teilgenommen haben, automatisch für die Teilnahme an der
-- Abschlussklausur registriert.

-- Erfolgreiche ZK-Teilnahme: Neuer Eintrag in StudentTeilnahmeKlausur mit 1.0 < note < 4.0 (check ob ID in ZK Table)

-- Registrierung für AK: Neuer Dummy-Eintrag in STK. Falls AK noch nicht existiert, neue Dummy-AK.
CREATE OR REPLACE FUNCTION registerAK() RETURNS trigger AS
$$
DECLARE
    isZK               BOOLEAN;
    vorlesungID        INTEGER;
    isGrundVL          BOOLEAN;
    abschlussklausurID INTEGER;
BEGIN
    -- Check if ZK
    isZK := (SELECT CASE
                        WHEN (NEW.klausur_id IN (TABLE zwischenklausur)) THEN
                            TRUE
                        ELSE
                            FALSE
                        END);
    -- Check if student passed
    IF NEW.note >= 1 AND NEW.note <= 4 AND isZK THEN
        -- Find possible corresponding AKs
        -- TODO: Testen ob isGrundVL funktioniert.
        vorlesungID := (SELECT coalesce(spezialvorlesung_id, grundvorlesung_id) FROM klausur WHERE id = NEW.klausur_id);
        isGrundVL := (SELECT CASE
                                 WHEN grundvorlesung_id IS NULL
                                     THEN FALSE
                                 ELSE TRUE
                                 END
                      FROM klausur
                      WHERE id = NEW.klausur_id);
        abschlussklausurID := (SELECT id
                               FROM klausur
                               WHERE coalesce(spezialvorlesung_id, grundvorlesung_id) = vorlesungID
                                 AND id != NEW.klausur_id
                                 AND id IN (TABLE abschlussklausur)
                               LIMIT 1);

        RAISE NOTICE 'ID der Zwischenklausur: %
                      ID der zug. VL: %
                      ID der zug. Abschlussklausur: %
                      isGrundVL: %', NEW.klausur_id, vorlesungID, abschlussklausurID, isGrundVL;

        -- Create dummy AK if not exists
        IF abschlussklausurID IS NULL THEN
            IF isGrundVL THEN
                INSERT INTO klausur (grundvorlesung_id) VALUES (vorlesungID) RETURNING id INTO abschlussklausurID;
            ELSE
                INSERT INTO klausur (spezialvorlesung_id) VALUES (vorlesungID) RETURNING id INTO abschlussklausurID;
            END IF;
            RAISE NOTICE 'Neue Abschlussklausur erstellt mit ID: %', abschlussklausurID;
            INSERT INTO abschlussklausur (klausur_id) VALUES (abschlussklausurID);
        END IF;

        -- Create new dummy entry in STK to mark registration
        INSERT INTO studentteilnahmeklausur (student_id, klausur_id) VALUES (NEW.student_id, abschlussklausurID);
    END IF;
    RETURN NEW;
END ;
$$ LANGUAGE plpgsql;


CREATE TRIGGER registerAK
    AFTER INSERT
    ON studentteilnahmeklausur
    FOR EACH ROW
EXECUTE FUNCTION registerAK();

-- Test-Funktionen
-- Einf. von neuer Teilnahme (Bsp. ZK 25)
INSERT INTO studentteilnahmeklausur (student_id, klausur_id, erschienen, entschuldigt, punkte, note)
VALUES (1, 25, NULL, NULL, 40.2, 2);

-- Erstelle neue ZK, die keine AK hat + zug. Vorlesung.
INSERT INTO veranstaltung (id)
VALUES (200);
INSERT INTO grundvorlesung (veranstaltung_id)
VALUES (200);
INSERT INTO klausur (id, grundvorlesung_id)
VALUES (100, 200);
INSERT INTO zwischenklausur (klausur_id)
VALUES (100);
INSERT INTO studentteilnahmeklausur (student_id, klausur_id, erschienen, entschuldigt, punkte, note)
VALUES (1, 100, NULL, NULL, 40.2, 2);


-- Erstellen Sie einen Trigger, welcher einen Studierenden automatisch zur
-- Wiederholungsklausur anmeldet, sobald er die reguläre Klausur versäumt hat
-- oder erfolglos teilgenommen hat.
CREATE OR REPLACE FUNCTION registerWK() RETURNS trigger AS
$$
DECLARE
    isAK                   BOOLEAN;
    vorlesungID            INTEGER;
    isGrundVL              BOOLEAN;
    wiederholungsklausurID INTEGER;
BEGIN
    -- Break if dummy entry
    IF NEW.erschienen IS NULL THEN
        RETURN NEW;
    END IF;
    -- Check if ZK
    isAK := (SELECT CASE
                        WHEN (NEW.klausur_id IN (TABLE abschlussklausur)) THEN
                            TRUE
                        ELSE
                            FALSE
                        END);

    -- Check if student passed
    IF NEW.erschienen IS FALSE OR (NEW.note < 1 OR NEW.note > 4) AND isAK THEN
        RAISE NOTICE 'Studierender % hat Klausur % nicht bestanden und wird zur Nachklausur angemeldet.', NEW.student_id, NEW.klausur_id;
        -- Find possible corresponding WK
        vorlesungID := (SELECT coalesce(spezialvorlesung_id, grundvorlesung_id) FROM klausur WHERE id = NEW.klausur_id);
        wiederholungsklausurID := (SELECT id
                                   FROM klausur
                                   WHERE coalesce(spezialvorlesung_id, grundvorlesung_id) = vorlesungID
                                     AND id != NEW.klausur_id
                                     AND id IN (TABLE wiederholungsklausur)
                                   LIMIT 1);
        isGrundVL := (SELECT CASE
                                 WHEN grundvorlesung_id IS NULL
                                     THEN FALSE
                                 ELSE TRUE
                                 END
                      FROM klausur
                      WHERE id = NEW.klausur_id);

        RAISE NOTICE 'ID der Abschlussklausur: %
                      ID der zug. VL: %
                      ID der zug. Wiederholungsklausur: %
                      isGrundVL: %', NEW.klausur_id, vorlesungID, wiederholungsklausurID, isGrundVL;

        -- Create dummy AK if not exists
        IF wiederholungsklausurID IS NULL THEN
            INSERT INTO klausur (grundvorlesung_id, datum, uhrzeitvon, gesamtpunktzahl)
            VALUES (vorlesungID, NULL, NULL, NULL) RETURNING id INTO wiederholungsklausurID;
            RAISE NOTICE 'Neue Wiederholungsklausur erstellt mit ID: %', wiederholungsklausurID;
            INSERT INTO abschlussklausur (klausur_id) VALUES (wiederholungsklausurID);
            INSERT INTO wiederholungsklausur (klausur_id) VALUES (wiederholungsklausurID);
        END IF;

        -- Create new dummy entry in STK to mark registration
        INSERT INTO studentteilnahmeklausur (student_id, klausur_id) VALUES (NEW.student_id, wiederholungsklausurID);

    END IF;
    RETURN NEW;
END ;
$$ LANGUAGE plpgsql;

CREATE TRIGGER registerWK
    AFTER INSERT OR UPDATE
    ON studentteilnahmeklausur
    FOR EACH ROW
EXECUTE FUNCTION registerWK();

-- Test-Funktionen
-- Zuerst bestanden => keine Anmeldung
-- Dann nicht bestanen => Anmeldung
UPDATE studentteilnahmeklausur
SET (erschienen, entschuldigt, punkte, note) = (TRUE, FALSE, 28, 2)
WHERE student_id = 1
  AND klausur_id = 58;

-- Zweiter Student => Schon vorhandene Klausur wird ausgewaehlt
INSERT INTO studentteilnahmeklausur (student_id, klausur_id, erschienen, entschuldigt, punkte, note)
VALUES (2, 100, NULL, NULL, 40.2, 2);

UPDATE studentteilnahmeklausur
SET (erschienen, entschuldigt, punkte, note) = (TRUE, FALSE, 28, 5)
WHERE student_id = 2
  AND klausur_id = 58;