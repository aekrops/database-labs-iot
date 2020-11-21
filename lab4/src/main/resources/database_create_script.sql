CREATE SCHEMA IF NOT EXISTS trostynskyi_db;
USE trostynskyi_db ;

DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS `match`;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS referees;
DROP TABLE IF EXISTS stadium;
DROP TABLE IF EXISTS team_statistic;


CREATE TABLE IF NOT EXISTS referees (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  age INT NOT NULL)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS stadium (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  city VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `match` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  season varchar(35) NOT NULL,
  guests_team varchar(35) NOT NULL,
  hosts_team varchar(35) NOT NULL,
  tournament varchar(35) NOT NULL,
  referee varchar(35) NOT NULL,
  stadium varchar(35) NOT NULL,
  match_date DATE NOT NULL)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS team (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  team_statistic_id INT NOT NULL,
  coach_id INT NOT NULL)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS team_statistic (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  victories INT NOT NULL,
  percentage_hits_on_target VARCHAR(45) NOT NULL)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS coach (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL,
  age INT NOT NULL)
ENGINE = InnoDB;

CREATE INDEX fk_team_statistic1_idx ON team(team_statistic_id, coach_id);
CREATE  UNIQUE INDEX coach_id_UNIQUE ON team (coach_id );
CREATE  UNIQUE INDEX team_statistic_id_UNIQUE ON team (team_statistic_id);
ALTER TABLE team
ADD CONSTRAINT fk_team_statistic1
FOREIGN KEY (team_statistic_id)
REFERENCES team_statistic (id),
ADD CONSTRAINT fk_team_coach1
FOREIGN KEY (coach_id)
REFERENCES coach (id);


CREATE TABLE IF NOT EXISTS player (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  team_id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  age INT NOT NULL)
ENGINE = InnoDB;

 CREATE INDEX fk_player_team1_idx ON player(team_id);
 ALTER TABLE player
 ADD CONSTRAINT fk_player_team1
 FOREIGN KEY (team_id)
 REFERENCES team (id);
 
 
INSERT INTO referees (id, name, age) VALUES
(1,'Hovard Vebb', 31),
(2,'Mike Din', 29),
(3,'Mark Klattenburg', 34),
(4,'Ken Aston', 25),
(5,'Grem Poll', 43),
(6,'Stuard Atvell', 39),
(7,'Martin Atkison', 41),
(8,'Michael Oliver', 40),
(9,'Andre Marriner', 24),
(10,'David Ellerey', 25);

INSERT INTO stadium (id, name, city, country) VALUES
(1, 'Old Trafford', 'Manchester', 'England'),
(2, 'Anfield', 'Liverpool', 'England'),
(3, 'Stamford Bridge', 'London', 'England'),
(4, 'Turf Moor', 'Lancashire', 'England'),
(5, 'Selhurst part', 'London', 'England'),
(6, 'Goodison park', 'Liverpool', 'England'),
(7, 'Carrow Road', 'Norwich', 'England'),
(8, 'The Den', 'London', 'England'),
(9, 'The Valley', 'London', 'England'),
(10, 'Crown Ground', 'Lancashire', 'England');


INSERT INTO team_statistic (id, victories, percentage_hits_on_target) VALUES
(1, 255, 52),
(2, 447, 54),
(3, 206, 43),
(4, 464, 57),
(5, 342, 53),
(6, 338, 58),
(7, 258, 44),
(8, 287, 53),
(9, 283, 61),
(10, 302, 51);

INSERT INTO coach (id, name, age) VALUES
(1, 'Nick Saban', 28),
(2, 'Jose Mourinho', 38),
(3, 'Hess Maizan', 41),
(4, 'Pete Carroll', 43),
(5, 'Jimbo Fisher', 27),
(6, 'Urban Meyer', 24),
(7, 'Woody Hayes', 36),
(8, 'Greg Skiano', 35),
(9, 'Ber Bryant', 24),
(10, 'Kevin Samlin', 24);


INSERT INTO team (id, name, team_statistic_id,coach_id) VALUES
(1, 'Real Madrid', 1, 1),
(2, 'Liverpool', 2, 2),
(3, 'Chelsea', 3, 3),
(4, 'Atletico', 4, 4),
(5, 'Barcelona', 5, 5),
(6, 'Bavaria', 6, 6),
(7, 'Arsenal', 7, 7),
(8, 'Juventus', 8, 8),
(9, 'Borussia', 9, 9),
(10, 'Celtic', 10, 10);

INSERT INTO `match` (id, season, guests_team, hosts_team, tournament, referee, stadium, match_date) VALUES
(1, 'League 1', 'Real Madrid', 'Liverpool', 'Premier League', 'Hovard Vebb', 'The Den', '2020-03-12'),
(2, 'Bundesleague', 'Liverpool', 'Arsenal', 'Bundesleague', 'Mike Din', 'Carrow Road', '2020-09-12'),
(3, 'Superleague', 'Juventus', 'Real Madrid', 'Cup of the king', 'Mark Klattenburg', 'Turf Moor', '2020-06-17'),
(4, 'Series A', 'Chelsea', 'Borussia', 'First division', 'David Ellerey', 'The Valley', '2020-11-12'),
(5, 'Premier league', 'Barcelona', 'Chelsea', 'Cup', 'Andre Marriner', 'Selhurst part', '2020-10-11'),
(6, 'Eliteserien', 'Liverpool', 'Atletico', 'Premiera division', 'Michael Oliver', 'Goodison park', '2020-06-12'),
(7, 'Premieira league', 'Atletico', 'Celtic', 'Seria A', 'Martin Atkison', 'Crown Ground', '2020-06-15'),
(8, 'La League', 'Barcelona', 'Chelsea', 'UEFA Nations league', 'Stuard Atvell', 'Anfield', '2020-03-19'),
(9, 'Premiera devision', 'Real Madrid', 'Borussia', 'UEFA champions', 'Grem Poll', 'Stamford Bridge', '2020-04-12'),
(10, 'A league', 'Atletico', 'Chelsea', 'UEFA Europe', 'Ken Aston', 'Old Trafford', '2020-07-23');


INSERT INTO player (id, team_id, name,age) VALUES
(1, 1, 'Ronaldo', 27),
(2, 5, 'Lionel Messi', 27),
(3, 5, 'Neymar', 27),
(4, 2, 'Mohammed Salah', 27),
(5, 5, 'Kevin De Braine', 27),
(6, 6, 'One Hazard', 27),
(7, 7, 'Sergio Aguero', 27),
(8, 5, 'Antoine Griezmann', 27),
(9, 1, 'Luka Modric', 27),
(10, 1, 'Sergio Ramos', 27);