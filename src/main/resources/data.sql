-- Le mot de passe est toto encrypté avec BCryptPasswordEncoder
Insert into utilisateur (ID, NOM, PRENOM, EMAIL, PASSWORD, ROLE) values (1, 'DUPONT', 'Marceline', 'admin@test','$2a$10$fEodLwtsfNiKLlO0gjAMLeVbs/C/QLFJhkmASVQK4yEkkzOgta/Bq','ADMIN') ;
Insert into utilisateur (ID, NOM, PRENOM, EMAIL, PASSWORD, ROLE) values (2, 'DUPONT', 'Gaston', 'user@test','$2a$10$fEodLwtsfNiKLlO0gjAMLeVbs/C/QLFJhkmASVQK4yEkkzOgta/Bq','USER') ;

insert into departement (ID, CODE, NOM) values (1, '36', 'Indre');
insert into departement (ID, CODE, NOM) values (2, '37', 'Indre-et-Loire');
insert into departement (ID, CODE, NOM) values (3, '49', 'Maine-et-Loire');
insert into departement (ID, CODE, NOM) values (4, '44', 'Loire-Atlantique');

insert into ville (id, nom, population, id_dept) values (1, 'Chateauroux', 42968, 1);
insert into ville (id, nom, population, id_dept) values (2, 'Issoudun', 10992, 1);
insert into ville (id, nom, population, id_dept) values (3, 'Déols', 7625, 1);
insert into ville (id, nom, population, id_dept) values (4, 'Le Blanc', 6204, 1);
insert into ville (id, nom, population, id_dept) values (5, 'Tours', 137658, 2);
insert into ville (id, nom, population, id_dept) values (6, 'Joué-lès-Tours', 38183, 2);
insert into ville (id, nom, population, id_dept) values (7, 'Saint-Cyr-sur-Loire', 17004, 2);
insert into ville (id, nom, population, id_dept) values (8, 'Saint-Pierre-des-Corps', 15909, 2);
insert into ville (id, nom, population, id_dept) values (9, 'Saint-Avertin', 15000, 2);
insert into ville (id, nom, population, id_dept) values (10, 'Amboise', 12938, 2);