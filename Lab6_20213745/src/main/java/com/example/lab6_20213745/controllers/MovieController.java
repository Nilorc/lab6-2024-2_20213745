package com.example.lab6_20213745.controllers;


import com.example.lab6_20213745.entity.Movie;
import com.example.lab6_20213745.repository.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping(value = {"", "/", "list"})
    public String listMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movie/list";
    }

    @GetMapping("/new")
    public String newMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie/form";
    }

    @PostMapping("/save")
    public String saveMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "movie/form";
        }
        movieRepository.save(movie);
        redirectAttributes.addFlashAttribute("msg", "Película guardada exitosamente");
        return "redirect:/movies";
    }

    @GetMapping("/edit")
    public String editMovie(Model model, @RequestParam("id") Long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            model.addAttribute("movie", optionalMovie.get());
            return "movie/form";
        } else {
            return "redirect:/movies";
        }
    }

    @GetMapping("/delete")
    public String deleteMovie(@RequestParam("id") Long id, RedirectAttributes attr) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            movieRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Película eliminada exitosamente");
        }
        return "redirect:/movies";
    }


}


