-- 1. Wie viel Prozent der Studierenden sind nicht im Studiengang Informatik eingeschrieben?
SELECT (
                   (SELECT COUNT(*) FROM student)::FLOAT -
                   (SELECT COUNT(*)
                    FROM student
                             JOIN studium ON student.id = studium.studentid
                             JOIN studiengang ON studium.studiengangid = studiengang.id
                    WHERE studiengang.name = 'Informatik')::FLOAT) /
       (SELECT COUNT(*) FROM student)::FLOAT AS prozent;


-- 2. Die Klausuren welcher Vorlesungsreihe (DBS1, DBS2, CDM, …) fanden durchschnittlich am frühsten statt?
-- In Klausurtabelle früheste Klausuren finden (Spezial und Grundvorlesungen mergen, früheste VL raussuchen), erhaltene ID in Veranstaltungen suchen
SELECT name
FROM veranstaltung
WHERE id = (SELECT gesamtid
            FROM (SELECT gesamtid, AVG(uhrzeitvon) AS avguhrzeitvon
                  FROM (SELECT spezialvorlesungid AS gesamtid, uhrzeitvon
                        FROM klausur
                        WHERE spezialvorlesungid IS NOT NULL
                        UNION ALL
                        SELECT grundvorlesungid AS gesamtid, uhrzeitvon
                        FROM klausur
                        WHERE grundvorlesungid IS NOT NULL) AS unionklausur
                  GROUP BY gesamtid
                  ORDER BY avguhrzeitvon
                  LIMIT 1) AS first);

-- 3. Wie viele Studierende haben im Wintersemester 2017 an der DBS1-Abschlussklausur teilgenommen?
SELECT COUNT(*)
FROM klausur
         JOIN studentteilnahmeklausur s ON s.klausurid = klausur.id
         JOIN (SELECT DISTINCT gesamtid
               FROM (SELECT spezialvorlesungid AS gesamtid
                     FROM klausur
                     WHERE spezialvorlesungid IS NOT NULL
                     UNION ALL
                     SELECT grundvorlesungid AS gesamtid
                     FROM klausur
                     WHERE grundvorlesungid IS NOT NULL) AS innerfoo) AS foo
              ON gesamtid = spezialvorlesungid OR gesamtid = grundvorlesungid
         JOIN veranstaltung v ON gesamtid = v.id
WHERE jahr = 2017
  AND semester = 'WiSe'
  AND name = 'DBS1'
  AND erschienen = TRUE;

-- 4. Welche Mitarbeiter der Abteilung haben noch nie eine Abschlussklausur beaufsichtigt?
-- Finde alle Mitarbeiter, die AKs beaufsichtigt haben. Gib restliche Mitarbeiter zurück.
SELECT vorname, nachname
FROM mitarbeiter
WHERE id NOT IN (SELECT mitarbeiterid
             FROM klausur
                      JOIN abschlussklausur a on klausur.id = a.klausurid
                      JOIN aufsicht a2 on klausur.id = a2.klausurid);

-- 5. Welche Veranstaltungsreihen wurden immer zur selben Zeit abgehalten?
-- Annahmen: Wochentag egal, Veranstaltungsreihe = gleicher Name
-- Also alle, die min. 1 mal vorkommen und zur selben Zeit stattfinden
-- abhaltung join veranstaltung, group by name & zeit, alle raus bei denen Name öfter als 1 Mal vorkommt, weil die zu unterschiedlichen Zeiten abgehalten wurden.
SELECT name
FROM (SELECT name,
             zeit
      FROM veranstaltung
               JOIN abhaltung on veranstaltung.id = abhaltung.veranstaltungid
      GROUP BY name, zeit) AS groupedVeranstaltungen
GROUP BY name
HAVING COUNT(name) < 2;

-- 6. Welche Studenten haben nur Grundvorlesungen besucht?
-- D.h. GV wurde besucht, aber SV wurde nicht besucht.
-- Annahme: Besucht heißt, Klausur wurde in dem Modul geschrieben.
-- Alle Studenten, die GV besucht haben minus die, die SV besucht haben
SELECT DISTINCT s2.id, s2.vorname, s2.nachname
FROM klausur
         JOIN studentteilnahmeklausur s on klausur.id = s.klausurid
         JOIN student s2 on s.studentid = s2.id
WHERE grundvorlesungid IS NOT NULL
    EXCEPT
SELECT DISTINCT s2.id, s2.vorname, s2.nachname
FROM klausur
         JOIN studentteilnahmeklausur s on klausur.id = s.klausurid
         JOIN student s2 on s.studentid = s2.id
WHERE spezialvorlesungid IS NOT NULL;

-- 7. Erstellen Sie eine Liste aller Studierenden geordnet nach der Anzahl der erfolgreich teilgenommenen Klausuren sowie Prüfungsleistungen.
-- erfolgreich = Note zwischen 1.0 und 4.0 (nicht erschienen hat Note 0.0)
SELECT id, vorname, nachname, teilnahmen
FROM (SELECT studentid, COUNT(studentid) as teilnahmen
      FROM (SELECT studentid
            FROM studentteilnahmeklausur
            WHERE note >= 1.0
              AND note <= 4.0
            UNION ALL
            SELECT studentid
            FROM studentteilnahmeveranstaltung
            WHERE note >= 1.0
              AND note <= 4.0) AS veranstaltungen
      GROUP BY studentid) AS gesamt
         JOIN student ON gesamt.studentid = student.id
ORDER BY teilnahmen DESC;

-- 8. Welche Studenten haben im Jahr 2016 und 2017 jeweils mindestens zwei Veranstaltungen zusammen besucht.

-- 9. Erstellen Sie ein Ranking über alle Studierenden zur Ermittlung der Top-Studierenden. Beachten Sie dabei die nachfolgenden Anforderungen:
-- Oberseminare und Zwischenklausuren sowie die entsprechenden Noten werden nicht berücksichtigt.
-- Es sollen nur Studierende berücksichtigt werden, welche mindestens zwei Veranstaltungen erfolgreich abgeschlossen haben.
-- Seminar- und Praktikumsnoten ergeben sich jeweils aus dem Mittel der Noten aus den Prüfungsteilleistungen.
-- Ermöglichen Sie eine unterschiedliche Gewichtung von Seminar-, Praktikums- und Klausurnoten.
-- Studierende, welche mehr als drei Veranstaltungen erfolgreich abgeschlossen haben, sollen einen individuell definierbaren Bonus auf ihren Rankingdurchschnitt erhalten