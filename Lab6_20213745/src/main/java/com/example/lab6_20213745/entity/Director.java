package com.example.lab6_20213745.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "directores")
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "directorId")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "telefono", nullable = false, length = 9)
    private String phoneNumber;

    @Column(name = "nacionalidad", nullable = false, length = 50)
    private String nationality;

    @ManyToMany(mappedBy = "directors")
    private List<Movie> movies;
}
