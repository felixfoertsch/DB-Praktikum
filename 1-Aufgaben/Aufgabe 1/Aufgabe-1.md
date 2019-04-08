# Aufgabe 1

In diesem Teil wird der erste Schritt der Erstellung einer vollständigen DB-Anwendung durchgeführt, der Datenbank-Entwurf. Dazu ist zunächst ein konzeptionelles Schema zu erstellen. Folgende Teilaufgaben sind für das Bestehen des ersten Testats zu lösen:

Überführen Sie das bereitgestellte UML-Diagramm in ein valides Relationenmodell. Benennen Sie alle Relationen und Attribute, die Sie für den Entwurf verwenden. Achten Sie darauf, Vererbungshierarchien korrekt ins Relationenmodell zu überführen. Entscheiden Sie je nach Kardinalität einer Beziehung, ob es sich um eine 1:1, 1:n oder n:m Beziehung handelt und erzeugen Sie die beteiligten Relationen entsprechend.

Entwickeln Sie anhand Ihres Relationenmodells ein SQL-DDL-Skript, welches die notwendigen Tabellen und Schlüsseldefinitionen und ggfs. weitere Constraints in PostgreSQL erzeugt. Definieren Sie auch entsprechende Lösch- und Updateregeln für die Fremdschlüsselbeziehungen.

Importieren Sie die bereitgestellten CSV-Daten in Ihre Datenbank. Achten Sie darauf, dass die bereitgestellten CSV-Daten nicht dem Zielschema entsprechen. Zum Laden der Daten ist ein SQL-Skript oder Java-Programm zu entwickeln, welches die CSV-Dateien einliest, entsprechend Ihrem Zielschema konvertiert und schließlich in die Datenbank schreibt. Für das Lesen der CSV-Dateien mittels Java empfehlen wir die Nutzung von Apache Commons CSV.

## Mini-Welt

Das bereitgestellte UML-Diagramm beschreibt die Mini-Welt der Abteilung Datenbanken, insbesondere hinsichtlich der Lehre. Die Abteilung Datenbanken bietet jedes Semester verschiedene Lehrveranstaltungen (Vorlesungen, Seminare, Praktika) an, welche von Studierenden besucht und von Mitarbeitern durchgeführt werden. Hierbei müssen jedes Semester verschiedene Daten gespeichert werden, vor allem sind studentische Teilnahmen und Leistungen zu erfassen.

## Abgaben

- Relationenmodell, inklusive aller in der Aufgabenstellung geforderten Angaben
- SQL-DDL-Skript zum Erzeugen der Datenbank
- SQL-Skript/Java-Programm zum Laden der CSV-Daten in die Datenbank

