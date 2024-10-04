package com.example.lab6_20213745.repository;

import com.example.lab6_20213745.dto.DirectorsByMovieDto;
import com.example.lab6_20213745.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {

    // Query para obtener el número de directores que han trabajado en cada película
    @Query(value = "SELECT p.titulo AS movieTitle, COUNT(d.directorId) AS numberOfDirectors " +
            "FROM peliculas p " +
            "INNER JOIN peliculas_directores pd ON p.peliculaId = pd.peliculaId " +
            "INNER JOIN directores d ON d.directorId = pd.directorId " +
            "GROUP BY p.peliculaId", nativeQuery = true)
    List<DirectorsByMovieDto> getDirectorsByMovie();

    // Query para encontrar directores por su nombre
    @Query(value = "SELECT * FROM directores WHERE nombre = ?1", nativeQuery = true)
    List<Director> findDirectorsByName(String name);
}