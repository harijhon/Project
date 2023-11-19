-- Création de la table Candidat
CREATE TABLE Candidat (
    candidat_id SERIAL PRIMARY KEY,
    nom_candidat VARCHAR(255) NOT NULL,
 
);

-- Création de la table Liste_electorale
CREATE TABLE Liste_electorale (
    liste_id SERIAL PRIMARY KEY,
    nom_liste VARCHAR(255) NOT NULL,
    bureau_vote_id INT REFERENCES Bureau_de_vote(bureau_id),

);


CREATE TABLE Candidature (
    candidature_id SERIAL PRIMARY KEY,
    liste_id INT REFERENCES Liste_electorale(liste_id),
    candidat_id INT REFERENCES Candidat(candidat_id),

);
