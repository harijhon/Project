--create database gendarme;
--use gendarme;

CREATE TABLE plainte(
    idPlainte int identity PRIMARY KEY,
    idOmby VARCHAR(10), --REFERENCES omby(idOmby)
    lieuPlainte VARCHAR(10), --REFERENCES localisation(idLoc)
    datePlainte date,
    lieuResolution VARCHAR(10) default null, --REFERENCES localisation(idLoc)
    dateResolution date default null
);
select * from plainte
--drop table plainte;
create table gendarme(
	idPlainte int identity PRIMARY KEY,
	idPersonne varchar(10)
);

update plainte set dateResolution='01-01-1940' where dateResolution is null