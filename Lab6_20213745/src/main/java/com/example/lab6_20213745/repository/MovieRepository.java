package com.example.lab6_20213745.repository;

import com.example.lab6_20213745.dto.DirectorsByMovieDto;
import com.example.lab6_20213745.dto.MoviesByDirectorDto;
import com.example.lab6_20213745.entity.Director;
import com.example.lab6_20213745.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // Query para obtener el número de películas dirigidas por cada director
    @Query(value = "SELECT d.nombre AS directorName, COUNT(p.peliculaId) AS numberOfMovies " +
            "FROM directores d " +
            "INNER JOIN peliculas_directores pd ON d.directorId = pd.directorId " +
            "INNER JOIN peliculas p ON p.peliculaId = pd.peliculaId " +
            "GROUP BY d.directorId", nativeQuery = true)
    List<MoviesByDirectorDto> getMoviesByDirector();

    // Query para encontrar películas por su título
    @Query(value = "SELECT * FROM peliculas WHERE titulo = ?1", nativeQuery = true)
    List<Movie> findMoviesByTitle(String title);
}
