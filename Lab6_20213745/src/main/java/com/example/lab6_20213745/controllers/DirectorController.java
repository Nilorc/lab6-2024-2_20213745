package com.example.lab6_20213745.controllers;

import com.example.lab6_20213745.entity.Director;
import com.example.lab6_20213745.repository.DirectorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/directors")
public class DirectorController {

    final DirectorRepository directorRepository;

    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @GetMapping(value = {"", "/", "list"})
    public String listDirectors(Model model) {
        model.addAttribute("directors", directorRepository.findAll());
        return "director/list";
    }

    @GetMapping("/new")
    public String newDirectorForm(Model model) {
        model.addAttribute("director", new Director());
        return "director/form";
    }

    @PostMapping("/save")
    public String saveDirector(@Valid @ModelAttribute("director") Director director, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "director/form";
        }
        directorRepository.save(director);
        redirectAttributes.addFlashAttribute("msg", "Director guardado exitosamente");
        return "redirect:/directors";
    }

    @GetMapping("/edit")
    public String editDirector(Model model, @RequestParam("id") Long id) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            model.addAttribute("director", optionalDirector.get());
            return "director/form";
        } else {
            return "redirect:/directors";
        }
    }

    @GetMapping("/delete")
    public String deleteDirector(@RequestParam("id") Long id, RedirectAttributes attr) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            directorRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Director eliminado exitosamente");
        }
        return "redirect:/directors";
    }
}
