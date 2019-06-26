ALTER TABLE klausur ADD column notenschluessel TEXT;
ALTER TABLE klausur ALTER COLUMN notenschluessel SET DEFAULT '95,90,85,80,75,70,65,60,55,50,50';