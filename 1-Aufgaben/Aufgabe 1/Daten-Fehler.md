# Umgang mit Daten-Fehler

Die Daten sind teilweise fehlerhaft. In diesem Dokument haben wir zusammengefasst, wie wir damit umgehen.

## Klausuren
- Manche Klausuren haben 10 Aufgaben -> Verletzt Modell, aber lassen wir gelten, da eine Klausur mit 10 Aufgaben nicht unüblich ist.
- Bei manchen Klausuren stimmt die eingetragenen Gesamtpunktzahl nicht mit der Summe der Klausuraufgaben überein -> Summe der Klausuraufgaben ist die Source of Truth; wir updaten die entsprechende Klausur

Nicht vorhandene Klausuren:
- 18ws_cdm
- 16ws_idbs2_wh

Doppelte Klausuraufgaben:
- 15ws_dbs2_wh
- 16ws_dbs2_wh
- 17ws_dbs2_wh

15ss_dbs1_wh hat keine zugehörige Veranstaltung, da 14ws_dbs1 nicht existiert -> Klausur wird belassen und hat keine Einträge in klausur (spezialvorlesungId, grundvorlesungId).


## Mitarbeiter
Herr Petermann ist nicht in bei den Mitarbeitern dabei -> Eintrag verwerfen, da er nur ein einziges mal als Betreuer einer Klausur auftaucht.

## Veranstaltungen

17ss_rivo ist weder Seminar noch Praktikum, sondern eine Ringvorlesung -> Zugehörige Seminar/Praktikumsteilnahmen werden 