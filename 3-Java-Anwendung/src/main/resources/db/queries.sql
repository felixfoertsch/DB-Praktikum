-- 1.1. Wie viel Prozent der Studierenden sind nicht im Studiengang Informatik eingeschrieben?
SELECT (
                   (SELECT COUNT(*) FROM student)::FLOAT -
                   (SELECT COUNT(*)
                    FROM student
                             JOIN studium ON student.id = studium.student_id
                             JOIN studiengang ON studium.studiengang_id = studiengang.id
                    WHERE studiengang.name = 'Informatik')::FLOAT) /
       (SELECT COUNT(*) FROM student)::FLOAT AS prozent;


-- 1.2. Die Klausuren welcher Vorlesungsreihe (DBS1, DBS2, CDM, …) fanden durchschnittlich am frühsten statt?
-- In Klausurtabelle früheste Klausuren finden (Spezial und Grundvorlesungen mergen, früheste VL raussuchen), erhaltene ID in Veranstaltungen suchen
SELECT name, AVG(uhrzeitvon) AS avguhrzeitvon
FROM (SELECT spezialvorlesung_id AS gesamtId, uhrzeitvon
      FROM klausur
      WHERE spezialvorlesung_id IS NOT NULL
      UNION ALL
      SELECT grundvorlesung_id AS gesamtId, uhrzeitvon
      FROM klausur
      WHERE grundvorlesung_id IS NOT NULL) as unionKlausur
         JOIN veranstaltung ON unionKlausur.gesamtId = veranstaltung.id
GROUP BY name
ORDER BY avguhrzeitvon
LIMIT 1;

-- 1.3. Wie viele Studierende haben im Wintersemester 2017 an der DBS1-Abschlussklausur teilgenommen?
SELECT COUNT(*)
FROM klausur
         JOIN studentteilnahmeklausur s ON s.klausur_id = klausur.id
         JOIN (SELECT DISTINCT gesamtid
               FROM (SELECT spezialvorlesung_id AS gesamtid
                     FROM klausur
                     WHERE spezialvorlesung_id IS NOT NULL
                     UNION ALL
                     SELECT grundvorlesung_id AS gesamtid
                     FROM klausur
                     WHERE grundvorlesung_id IS NOT NULL) AS innerfoo) AS foo
              ON gesamtid = spezialvorlesung_id OR gesamtid = grundvorlesung_id
         JOIN veranstaltung v ON gesamtid = v.id
WHERE jahr = 2017
  AND semester = 'WiSe'
  AND name = 'DBS1'
  AND erschienen = TRUE;

-- 1.4. Welche Mitarbeiter der Abteilung haben noch nie eine Abschlussklausur beaufsichtigt?
-- Finde alle Mitarbeiter, die AKs beaufsichtigt haben. Gib restliche Mitarbeiter zurück.
SELECT vorname, nachname
FROM mitarbeiter
WHERE id NOT IN (SELECT mitarbeiter_id
                 FROM klausur
                          JOIN abschlussklausur a ON klausur.id = a.klausur_id
                          JOIN aufsicht a2 ON klausur.id = a2.klausur_id);

-- 1.5. Welche Veranstaltungsreihen wurden immer zur selben Zeit abgehalten?
-- Annahmen: Wochentag egal, Veranstaltungsreihe = gleicher Name
-- Also alle, die min. 1 mal vorkommen und zur selben Zeit stattfinden
-- abhaltung join veranstaltung, group by name & zeit, alle raus bei denen Name öfter als 1 Mal vorkommt, weil die zu unterschiedlichen Zeiten abgehalten wurden.
SELECT name
FROM (SELECT name,
             zeit
      FROM veranstaltung
               JOIN abhaltung ON veranstaltung.id = abhaltung.veranstaltung_id
      GROUP BY name, zeit) AS groupedveranstaltungen
GROUP BY name
HAVING COUNT(name) < 2;

-- 1.6. Welche Studenten haben nur Grundvorlesungen besucht?
-- D.h. GV wurde besucht, aber SV wurde nicht besucht.
-- Annahme: Besucht heißt, Klausur wurde in dem Modul geschrieben.
-- Alle Studenten, die GV besucht haben minus die, die SV besucht haben
SELECT DISTINCT s2.id, s2.vorname, s2.nachname
FROM klausur
         JOIN studentteilnahmeklausur s ON klausur.id = s.klausur_id
         JOIN student s2 ON s.student_id = s2.id
WHERE grundvorlesung_id IS NOT NULL
    EXCEPT
SELECT DISTINCT s2.id, s2.vorname, s2.nachname
FROM klausur
         JOIN studentteilnahmeklausur s ON klausur.id = s.klausur_id
         JOIN student s2 ON s.student_id = s2.id
WHERE spezialvorlesung_id IS NOT NULL;

-- 1.7. Erstellen Sie eine Liste aller Studierenden geordnet nach der Anzahl der erfolgreich teilgenommenen Klausuren sowie Prüfungsleistungen.
-- erfolgreich = Note zwischen 1.0 und 4.0 (nicht erschienen hat Note 0.0)
SELECT id, vorname, nachname, teilnahmen
FROM (SELECT student_id, COUNT(student_id) AS teilnahmen
      FROM (SELECT student_id
            FROM studentteilnahmeklausur
            WHERE note >= 1.0
              AND note <= 4.0
            UNION ALL
            SELECT student_id
            FROM studentteilnahmeveranstaltung
            WHERE note >= 1.0
              AND note <= 4.0) AS veranstaltungen
      GROUP BY student_id) AS gesamt
         JOIN student ON gesamt.student_id = student.id
ORDER BY teilnahmen DESC;

-- 1.8. Welche Studenten haben im Jahr 2016 und 2017 jeweils mindestens zwei Veranstaltungen zusammen besucht.
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
             SELECT klausur.id AS klausur_id,
                    coalesce(spezialvorlesung_id, grundvorlesung_id) AS veranstaltung_id,
                    jahr
             FROM veranstaltung AS v
                      JOIN klausur
                           ON coalesce(spezialvorlesung_id, grundvorlesung_id) = v.id) AS k
             JOIN studentteilnahmeklausur
                  ON studentteilnahmeklausur.klausur_id = k.klausur_id
)
SELECT concat(linkerStudent.vorname, ' ', linkerStudent.nachname) AS student_1, -- Infos der Studierenden retrieven
       linkerStudent.matrikelnr AS mnr_1,
       links AS id_1,
       concat(rechterStudent.vorname, ' ', rechterStudent.nachname) AS student_2,
       rechterStudent.matrikelnr AS mnr_2,
       rechts AS id_2
FROM (
         SELECT DISTINCT a.student_id AS links, b.student_id rechts -- DISTINCT, da lernpartner-Eigenschaft in mehrere
         FROM veranstaltungsMatching AS a
                  FULL JOIN veranstaltungsMatching AS b ON a.veranstaltung_id = b.veranstaltung_id
         WHERE a.student_id < b.student_id
           AND a.jahr = 2016
         GROUP BY a.student_id, b.student_id -- a und b sind in einer Veranstaltung, und durch a.jahr im selben Jahr
         HAVING count(*) >= 2

         INTERSECT  -- 2016 und 2017 JEWEILS min. 2 Veranstaltungen besucht

         SELECT DISTINCT a.student_id AS links, b.student_id
         FROM veranstaltungsMatching AS a
                  FULL JOIN veranstaltungsMatching AS b ON a.veranstaltung_id = b.veranstaltung_id
         WHERE a.student_id < b.student_id
           AND a.jahr = 2017
         GROUP BY a.student_id, b.student_id
         HAVING count(*) >= 2) AS ids
         JOIN student AS linkerStudent ON ids.links = linkerStudent.id
         JOIN student AS rechterStudent ON ids.rechts = rechterstudent.id;

-- 1.9. Erstellen Sie ein Ranking über alle Studierenden zur Ermittlung der Top-Studierenden. Beachten Sie dabei die nachfolgenden Anforderungen:
-- Oberseminare und Zwischenklausuren sowie die entsprechenden Noten werden nicht berücksichtigt.
-- Es sollen nur Studierende berücksichtigt werden, welche mindestens zwei   Veranstaltungen erfolgreich abgeschlossen haben.
-- Seminar- und Praktikumsnoten ergeben sich jeweils aus dem Mittel der Noten aus den Prüfungsteilleistungen.
-- Ermöglichen Sie eine unterschiedliche Gewichtung von Seminar-, Praktikums- und Klausurnoten.
-- Studierende, welche mehr als drei Veranstaltungen erfolgreich abgeschlossen haben, sollen einen individuell definierbaren Bonus auf ihren Rankingdurchschnitt erhalten


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
                 SELECT klausur.id AS klausur_id,
                        coalesce(spezialvorlesung_id, grundvorlesung_id) AS veranstaltung_id,
                        jahr
                 FROM veranstaltung AS v
                          JOIN klausur
                               ON coalesce(spezialvorlesung_id, grundvorlesung_id) = v.id) AS k
                 JOIN studentteilnahmeklausur
                      ON studentteilnahmeklausur.klausur_id = k.klausur_id
    )
    SELECT DISTINCT a.student_id AS links, b.student_id rechts -- DISTINCT, da lernpartner-Eigenschaft in mehrere
    FROM veranstaltungsMatching AS a
             FULL JOIN veranstaltungsMatching AS b ON a.veranstaltung_id = b.veranstaltung_id
    WHERE a.student_id < b.student_id
    GROUP BY a.jahr, a.student_id, b.student_id -- a und b sind in einer Veranstaltung, und durch a.jahr im selben Jahr
    HAVING count(*) >= 3;


-- Lerpartner ersten Grades
SELECT id, concat(vorname, ' ', nachname), matrikelnr
FROM(
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
    WHERE matrikelnr = '3029075'

    UNION

    SELECT links AS partnerId
    FROM lernpartner
             JOIN student ON rechts = id
    WHERE matrikelnr = '3029075'
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