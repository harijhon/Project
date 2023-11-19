CREATE DATABASE election;

CREATE table Region (
    id_region SERIAL primary key,
    nom_region VARCHAR(255) NOT NULL,
);

CREATE TABLE District (
    district_id SERIAL PRIMARY KEY,
    nom_district VARCHAR(255) NOT NULL,
    region_id INT,
    foreign key region_id references Region(id_region)
);

CREATE TABLE bureau_vote (
    bureau_vote_id SERIAL PRIMARY KEY,
    nom_bureau VARCHAR(255) NOT NULL,
    district_id INT,
    foreign key District references District(district_id)
);

