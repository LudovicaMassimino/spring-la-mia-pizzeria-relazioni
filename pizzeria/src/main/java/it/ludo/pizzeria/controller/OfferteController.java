package it.ludo.pizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.ludo.pizzeria.model.OfferteSpeciali;
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

    @GetMapping("/dettaglio/{pizzaId}/create-offerte")
    public String createSpecialOffer(@PathVariable("pizzaId") Integer pizzaId, Model model) {
        PizzaMod pizza = pizzaRepo.findById(pizzaId).orElse(null);
        if (pizza == null) {
            return "redirect:/pizzeria/menu";
        }

        OfferteSpeciali offerte = new OfferteSpeciali();
        offerte.setPizza(pizza);
        model.addAttribute("offerte", offerte);
        return "/pizzeria/create-offerte";
    }

    @PostMapping("/dettaglio/{pizzaId}/create-offerte")
    public String storeSpecialOffer(@PathVariable("pizzaId") Integer pizzaId, @Valid @ModelAttribute("offerte") OfferteSpeciali offerte,
                                    BindingResult bindingResult, Model model) {
        PizzaMod pizza = pizzaRepo.findById(pizzaId).orElse(null);
        if (pizza == null) {
            return "redirect:/pizzeria/menu";
        }

        if (bindingResult.hasErrors()) {
            return "/pizzeria/create-offerte";
        }

        offerte.setPizza(pizza);
        offSpecialiRepo.save(offerte);
        return "redirect:/pizzeria/dettaglio/" + pizzaId;
    }

    @GetMapping("/dettaglio/edit-offerte/{offerteId}")
    public String editSpecialOffer(@PathVariable("offerteId") Integer offerteId, Model model) {
        OfferteSpeciali offerte = offSpecialiRepo.findById(offerteId).orElse(null);
        if (offerte == null) {
            return "redirect:/pizzeria/menu";
        }

        model.addAttribute("offerte", offerte);
        return "/pizzeria/edit-offerte";
    }

    @PostMapping("/dettaglio/edit-offerte/{offerteId}")
    public String updateSpecialOffer(@PathVariable("offerteId") Integer offerteId, @Valid @ModelAttribute("offerte") OfferteSpeciali offerte,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzeria/edit-offerte";
        }

        offSpecialiRepo.save(offerte);
        return "redirect:/pizzeria/dettaglio/" + offerte.getPizza().getId();
    }
}
