package it.ludo.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ludo.pizzeria.model.OfferteSpecialiMod;
import it.ludo.pizzeria.model.PizzaMod;
import it.ludo.pizzeria.repository.OffSpecialiRepo;
import it.ludo.pizzeria.repository.PizzaRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OfferteController {

    @Autowired
    private OffSpecialiRepo offSpecialiRepo;

    @Autowired
    private PizzaRepo pizzaRepo;

    @GetMapping
    public String getListaOfferte(Model model) {
        List<OfferteSpecialiMod> listaOfferte = offSpecialiRepo.findAll();
        model.addAttribute("listaOfferte", listaOfferte);
        return "offerte/lista";
    }

    @GetMapping("/create-offerte")
    public String createOfferte(Model model) {
        List<PizzaMod> pizzaTarget = pizzaRepo.findAll();
        model.addAttribute("offerte", new OfferteSpecialiMod());
        model.addAttribute("pizzaTarget", pizzaTarget);
        return "offerte/create-offerte";
    }

    @PostMapping("/create-offerte")
    public String store(@Valid @ModelAttribute("offerte") OfferteSpecialiMod offerteForm,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizzaTarget", pizzaRepo.findAll());
            return "offerte/create-offerte";
        }

        offSpecialiRepo.save(offerteForm);
        return "redirect:/offerte";
    }

    @GetMapping("/edit-offerte/{offerteId}")
    public String editSpecialOffer(@PathVariable("offerteId") Integer offerteId, Model model) {
        OfferteSpecialiMod offerte = offSpecialiRepo.findById(offerteId).orElse(null);
        if (offerte == null) {
            return "redirect:/offerte";
        }

        model.addAttribute("offerte", offerte);
        model.addAttribute("pizzaTarget", pizzaRepo.findAll()); // Aggiungi le opzioni di pizza per la modifica
        return "offerte/edit-offerte";
    }

    @PostMapping("/edit-offerte/{offerteId}")
    public String updateSpecialOffer(@PathVariable("offerteId") Integer offerteId,
            @Valid @ModelAttribute("offerte") OfferteSpecialiMod offerte,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizzaTarget", pizzaRepo.findAll());
            return "offerte/edit-offerte";
        }

        // Aggiornare l'offerta corretta
        offerte.setID(offerteId);
        offSpecialiRepo.save(offerte);
        return "redirect:/offerte";
    }
}
