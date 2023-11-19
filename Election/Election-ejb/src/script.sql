
CREATE table Region (
    id_region SERIAL primary key,
    nom_region VARCHAR(255) NOT NULL
);
select * from region

CREATE TABLE District (
    district_id SERIAL PRIMARY KEY,
    nom_district VARCHAR(255) NOT NULL,
    id_region INT,
    foreign key (id_region) references region(id_region)
);


CREATE TABLE bureau_vote (
    bureau_vote_id SERIAL PRIMARY KEY,
    nom_bureau VARCHAR(255) NOT NULL,
    district_id INT,
    foreign key (district_id) references District(district_id)
);

ALTER TABLE bureau_vote
ADD COLUMN olona_afaka_mifidy INT default 0;

ALTER TABLE bureau_vote
ADD COLUMN nbre_olona_nifidy INT default 0;

ALTER TABLE bureau_vote
ADD COLUMN nbre_olona_voafidy INT default 0;

ALTER TABLE bureau_vote
ADD COLUMN nbre_vato_mety INT default 0;


select * from District



CREATE TABLE Candidat (
    candidat_id SERIAL PRIMARY KEY,
    nom_candidat VARCHAR(255) NOT NULL 
);

CREATE TABLE Liste_electorale (
    liste_id SERIAL PRIMARY KEY,
    nom_liste VARCHAR(255) NOT NULL,
    bureau_vote_id INT,
    CIN VARCHAR(255) NOT NULL,
    foreign key (bureau_vote_id) REFERENCES Bureau_vote(bureau_vote_id) 
);

CREATE TABLE Resultat (
    bureau_vote_id INT,
    candidat_id INT,
    nombre_vote INT,
    foreign key (bureau_vote_id) references bureau_vote(bureau_vote_id),
    foreign key (candidat_id) references candidat(candidat_id)
);


--SCRIPT POUR INSERTION---
-- Insérer des données dans la table "Region"
INSERT INTO Region (nom_region) VALUES ('Analamanga');
INSERT INTO Region (nom_region) VALUES ('Itasy');
-- Ajoutez trois autres régions selon vos besoins.

-- Insérer des données dans la table "District"
INSERT INTO District (nom_district, id_region) VALUES ('Antananarivo', 1);
INSERT INTO District (nom_district, id_region) VALUES ('Arivonimamo', 1);
-- Ajoutez trois autres districts selon vos besoins.

-- Insérer des données dans la table "bureau_vote"
INSERT INTO bureau_vote (nom_bureau, district_id, nbre_olona_afaka_mifidy, nbre_olona_nifidy, nbre_olona_voafidy, nbre_vato_mety) 
VALUES ('Bureau A', 1, 10, 20, 5, 15);
INSERT INTO bureau_vote (nom_bureau, district_id, nbre_olona_afaka_mifidy, nbre_olona_nifidy, nbre_olona_voafidy, nbre_vato_mety) 
VALUES ('Bureau B', 2, 8, 15, 3, 12);
-- Ajoutez trois autres bureaux de vote selon vos besoins.

-- Insérer des données dans la table "Candidat"
INSERT INTO Candidat (nom_candidat) VALUES ('Tahina Razafinjoelina');
INSERT INTO Candidat (nom_candidat) VALUES ('Andry Rajoelina');
-- Ajoutez trois autres candidats selon vos besoins.

-- Insérer des données dans la table "Liste_electorale"
INSERT INTO Liste_electorale (nom_liste, bureau_vote_id, CIN) VALUES ('Liste 1', 1, '123456789');
INSERT INTO Liste_electorale (nom_liste, bureau_vote_id, CIN) VALUES ('Liste 2', 2, '987654321');
-- Ajoutez trois autres listes électorales selon vos besoins.

-- Insérer des données dans la table "Resultat"
INSERT INTO Resultat (bureau_vote_id, candidat_id, nombre_vote) VALUES (1, 1, 50);
INSERT INTO Resultat (bureau_vote_id, candidat_id, nombre_vote) VALUES (2, 2, 45);
CREATE VIEW vue_nbre_olona_afaka_mifidy_par_bureau AS
SELECT
    bureau_vote_id,
    nom_bureau,
    nbre_olona_afaka_mifidy
FROM
    bureau_vote;


--CANDIDAT PAR REGION
CREATE VIEW view_resultat_par_candidat_par_region AS
SELECT
    R.candidat_id,
    C.nom_candidat,
    D.id_region,
    R.bureau_vote_id,
    COUNT(*) AS nombre_de_votes
FROM
    Resultat R
JOIN
    Candidat C ON R.candidat_id = C.candidat_id
JOIN
    bureau_vote BV ON R.bureau_vote_id = BV.bureau_vote_id
JOIN
    District D ON BV.district_id = D.district_id
GROUP BY
    R.candidat_id, C.nom_candidat, D.id_region, R.bureau_vote_id;

    

--CANDIDAT PAR DISTRICT
CREATE VIEW view_resultat_par_candidat_par_district AS
SELECT
    R.candidat_id,
    C.nom_candidat,
    D.nom_district,
    R.bureau_vote_id,
    COUNT(*) AS nombre_de_votes
FROM
    Resultat R
JOIN
    Candidat C ON R.candidat_id = C.candidat_id
JOIN
    bureau_vote BV ON R.bureau_vote_id = BV.bureau_vote_id
JOIN
    District D ON BV.district_id = D.district_id
GROUP BY
    R.candidat_id, C.nom_candidat, D.nom_district, R.bureau_vote_id;

--Pourcentage par candidat
CREATE VIEW view_percentage_par_candidat AS
SELECT
    R.candidat_id,
    C.nom_candidat,
    COUNT(*) AS nombre_de_votes,
    COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (PARTITION BY R.candidat_id) AS pourcentage
FROM
    Resultat R
JOIN
    Candidat C ON R.candidat_id = C.candidat_id
GROUP BY
    R.candidat_id, C.nom_candidat;

--Vote restant par bureau de vote
CREATE VIEW view_votes_restants_par_bureau AS
SELECT
    BV.bureau_vote_id,
    BV.nom_bureau,
    BV.nbre_olona_afaka_mifidy - COALESCE(SUM(R.nombre_vote), 0) AS votes_restants
FROM
    bureau_vote BV
LEFT JOIN
    Resultat R ON BV.bureau_vote_id = R.bureau_vote_id
GROUP BY
    BV.bureau_vote_id, BV.nom_bureau, BV.nbre_olona_afaka_mifidy;















