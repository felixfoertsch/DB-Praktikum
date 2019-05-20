# Umgang mit Daten-Fehler

Die Daten sind teilweise fehlerhaft. In diesem Dokument haben wir zusammengefasst, wie wir damit umgehen.

## Klausuren-Aufgaben
- Manche Klausuren haben 10 Aufgaben
  Folge: Verletzt Modell, aber lassen wir gelten, da eine Klausur mit 10 Aufgaben nicht unüblich ist.
- Bei manchen Klausuren stimmt die eingetragenen Gesamtpunktzahl nicht mit der Summe der Klausuraufgaben überein
  Folge: Summe der Klausuraufgaben ist die Source of Truth; wir updaten die entsprechende Klausur

### Nicht vorhandene Klausuren
- 18ws_cdm
- 16ws_idbs2_wh
- 18ws_idbs2_wh

Folge: Es gibt weder Klausur, noch VA, noch Ergebnisse -> verwerfen

### Doppelte Klausuraufgaben
- 15ws_dbs2_wh
- 16ws_dbs2_wh
- 17ws_dbs2_wh

Folge: Es wird immer der erste Datenblock mit 9 Aufgaben gewählt, da bei den Bearbeitungen 8 Aufgaben bearbeitet wurden und der zweite Block nur 7 Aufgaben hat.

### 15ws_idbs2_wh
- Klausur-Aufgaben existieren nicht
- Klausur selbst existiert
- Es gibt Klausur-Ergebnisse der studierenden (Klausurpunkte-Datei)

Folge: Es werden Blanko-Aufgaben mit 0 maxPunkten angelegt, um die Klausur-Ergebnisse zu erhalten.

### 15ss_dbs1_wh
15ss_dbs1_wh (ID 14) hat keine zugehörige Veranstaltung, da 14ws_dbs1 nicht existiert

Folge: Klausur wird belassen und hat keine Einträge in klausur (spezialvorlesungId, grundvorlesungId).

## Mitarbeiter
Herr Petermann ist nicht in bei den Mitarbeitern dabei.

Folge: Eintrag verwerfen, da er nur ein einziges mal als Betreuer einer Klausur auftaucht.

## Veranstaltungen
17ss_rivo ist weder Seminar noch Praktikum, sondern eine Ringvorlesung.

Folge: Es wird eine zugehörige Klausur angelegt und die Daten werden dort mit eingetragen 