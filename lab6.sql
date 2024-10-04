-- Este script tiene 3 segmentos, para utilizarlo deben crear un schema de base de datos
-- Ejecutar cada segmento por separado.

-- movie_master.peliculas definition

DROP DATABASE IF EXISTS lab6;
CREATE SCHEMA IF NOT EXISTS lab6 DEFAULT CHARACTER SET utf8mb3 ;
USE lab6 ;

CREATE TABLE peliculas (
  peliculaId int NOT NULL AUTO_INCREMENT,
  titulo varchar(100) DEFAULT NULL,
  fechaEstreno date DEFAULT NULL,
  duracionMinutos int DEFAULT NULL,
  PRIMARY KEY (peliculaId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- movie_master.directores definition

CREATE TABLE directores (
  directorId int NOT NULL AUTO_INCREMENT,
  nombre varchar(100) DEFAULT NULL,
  telefono varchar(9) DEFAULT NULL,
  nacionalidad varchar(50) DEFAULT NULL,
  PRIMARY KEY (directorId)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- movie_master.peliculas_directores definition (relaciona películas con directores)

CREATE TABLE peliculas_directores (
  peliculaId int NOT NULL,
  directorId int NOT NULL,
  PRIMARY KEY (peliculaId, directorId),
  KEY peliculas_directores_pelicula_FK (peliculaId),
  KEY peliculas_directores_director_FK (directorId),
  CONSTRAINT peliculas_directores_pelicula_FK FOREIGN KEY (peliculaId) REFERENCES peliculas (peliculaId),
  CONSTRAINT peliculas_directores_director_FK FOREIGN KEY (directorId) REFERENCES directores (directorId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




