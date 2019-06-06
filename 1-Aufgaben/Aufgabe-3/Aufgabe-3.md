Ihre Aufgabe ist die Implementierung einer Java-Anwendung zum Zugriff auf die Datenbank. Ziel ist es, Ihnen die Technik von 3-Ebenen-Anwendungen (Frontend/Datensicht, Mittelschicht, Backend/Datenbank) näher zu bringen, Möglichkeiten des datenbankunabhängigen Zugriffs kennenzulernen sowie erweiterte SQL-Konzepte anzuwenden.

1. Objekt-relationales Mapping mit Hibernate:

Überführen Sie Ihr Datenbankschema in ein objektorientiertes Modell und entsprechende Java-Klassen. Diese sollen dann mittels Hibernate auf die Datenbanktabellen abgebildet werden. Erstellen Sie dazu das entsprechende objekt-relationale Mapping unter Verwendung von Annotationen. Achten Sie auf die korrekte Abbildung von Vererbungshierachien und Beziehungen, insbesondere auf Beziehungen mit zusätzlichen Attributen.

2. Java-Anwendung:

Use Case I: Klausurergebnisse eintragen

Liste aller Klausuren anzeigen, geordnet nach Datum
Nutzer wählt Klausur aus
Eingabe Matrikelnummer
DB-Zugriff -> Ist Student in DB vorhanden und für Klausur angemeldet?
Ja: Eingabe der Punkte
Test, ob Eingabe korrekt + Ausgabe Gesamtpunktzahl
Gehe zu 3. bis alle Studenten verarbeitet sind
Anzeige aller abwesenden Studenten
Use Case II: Klausurergebnisse (Notenschnitt) einsehen und anpassen

Liste aller Klausuren anzeigen, geordnet nach Datum
Nutzer wählt Klausur aus
Anzeige Noten-Verteilung (Histogram) oder Anzeige Punkte-Noten-Verteilung oder Anzeige geordnete Liste aller Studenten mit Note und Punkte oder Anpassung Punkte-Noten-Verteilung oder Exit
Gehe zu 3. solange nicht exit
Use Case III: Top-Studenten

Eingabe der Gewichtungen
Anzeige der Liste aller Top-Studenten (Nutzung View)
Programm-Teil A: API (Mittelschicht)

Regelt den Zugriff auf die Datenbank
Stellt notwendige Operationen für die obigen Use Cases bereit
Programm-Teil B: GUI / Konsolen-Anwendung (Frontend)

Implementierung GUI bzw. Konsolen-Logik zur Abarbeitung/Ausführung der obigen Use Cases
Abgabe vor dem Testat

Bitte setzen Sie sich per E-Mail mit einem Terminvorschlag für Ihr Testat mit Ihrem Betreuer in Verbindung. Bitte geben Sie im Betreff der E-Mail dbprak sowie die Gruppennummer an. Nehmen Sie dabei ihren Gruppenpartner mit ins CC, damit wir mit Reply-All beiden antworten können. Spätestens 24h vor dem 3. Testat müssen Sie Ihrem Betreuer die nachfolgend beschriebenen Dateien zur Verfügung stellen. Melden Sie sich rechtzeitig per Email!
Abzugeben sind:

Alle notwendigen Dateien zur Ausführung Ihrer Anwendung mit entsprechender Dokumentation.
Die Funktionalitäten der Anwendung werden während des Testats ausgeführt und getestet.
Links

Hibernate - Nutzen Sie eine aktuelle Version von Hibernate.
Hibernate Tutorial