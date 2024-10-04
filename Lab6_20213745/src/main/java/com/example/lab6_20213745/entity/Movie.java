package com.example.lab6_20213745.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Setter
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peliculaId")
    private Long id;

    @Column(name = "titulo", nullable = false, length = 100)
    private String title;

    @Column(name = "fechaEstreno", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "duracionMinutos", nullable = false)
    private int duration;

    @ManyToMany
    @JoinTable(
            name = "peliculas_directores",  // Nombre correcto de la tabla de relación
            joinColumns = @JoinColumn(name = "peliculaId"),  // Nombre correcto del campo de película en la tabla de relación
            inverseJoinColumns = @JoinColumn(name = "directorId")  // Nombre correcto del campo de director en la tabla de relación
    )
    private List<Director> directors;
}
