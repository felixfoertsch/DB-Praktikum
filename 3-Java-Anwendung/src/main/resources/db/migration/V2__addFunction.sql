CREATE OR REPLACE FUNCTION topStudenten(weightKlausur INTEGER, weightPrak INTEGER, weightSem INTEGER, bonusNote FLOAT)
    RETURNS TABLE (
                      id INTEGER,
                      matrikelnr TEXT,
                      vorname TEXT,
                      nachname TEXT,
                      unimail TEXT
                  )
AS
$$
BEGIN
    RETURN QUERY
        SELECT student.id, student.matrikelnr, student.vorname, student.nachname, student.unimail
        FROM (SELECT student_id,
                     anzver,
                     CASE WHEN anzver >= 3 THEN bonusNote ELSE 0 END AS bonus,
                     score
              FROM (SELECT student_id,
                           COUNT(*) as anzver,
                           SUM(note * weight)/SUM(weight) AS score
                    FROM (SELECT student_id, -- 967 rows
                                 note,
                                 weightKlausur AS weight -- weight bestimmt, wie oft die Note in die Wertung eingeht (ganzzahlig)
                          FROM studentteilnahmeklausur
                          WHERE klausur_id IN (TABLE abschlussklausur)
                            AND note >= 1.0
                            AND note <= 4.0-- Es existieren im Datensatz keine Teilnahmen an Zwischenklausuren
                          UNION ALL
                          SELECT student_id, -- 273 rows
                                 note,
                                 weightPrak AS weight
                          FROM studentteilnahmeveranstaltung
                          WHERE veranstaltung_id IN (TABLE praktikum)
                            AND note >= 1.0
                            AND note <= 4.0
                          UNION ALL
                          SELECT student_id, -- 40 rows
                                 note,
                                 weightSem AS weight
                          FROM studentteilnahmeveranstaltung
                          WHERE veranstaltung_id IN (TABLE seminar EXCEPT (TABLE oberseminar))
                            AND note >= 1.0
                            AND note <= 4.0
                         ) AS a
                    GROUP BY student_id
                    HAVING COUNT(*) >= 2) AS b) AS c
                 JOIN student ON student_id = student.id
        ORDER BY score;
END;
$$ LANGUAGE plpgsql;


