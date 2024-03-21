CREATE TABLE utilisateur (ID int NOT NULL PRIMARY KEY, NOM varchar(50) NOT NULL, PRENOM varchar(50) NOT NULL, EMAIL varchar(50) NOT NULL, PASSWORD varchar(200) NOT NULL, ROLE varchar(10) NOT NULL);
-- INSERTIONS
Insert into utilisateur (ID, NOM, PRENOM, EMAIL, PASSWORD, ROLE) values (1, 'BONNAMY', 'Richard', 'rbonnamy@diginamic.fr','toto','ADMIN') ;
