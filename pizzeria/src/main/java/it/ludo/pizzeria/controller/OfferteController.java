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

        java.util.List<OfferteSpecialiMod> listaOfferte = offSpecialiRepo.findAll();
        model.addAttribute("listaOfferte", listaOfferte);

        return "/offerte/lista";
    }

    @GetMapping("/create-offerte")
    public String createOfferte(@PathVariable("pizzaId") Integer pizzaId, Model model) {
        List<PizzaMod> pizzaTarget = pizzaRepo.findAll();

        model.addAttribute("offerte", new OfferteSpecialiMod());
        model.addAttribute("pizzaTarget", pizzaTarget);

        return "offerte/create-offerte";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("Offerte") OfferteSpecialiMod OfferteForm, BindingResult bindingresult, Model model) {
        
        if (bindingresult.hasErrors()) {
            System.out.println("dentro if errore");

            return "/offerte/create-";
        }
        System.err.println("fuori if errore");

        System.out.println(OfferteForm.getPizza());

        offSpecialiRepo.save(OfferteForm);

        return "redirect:/sale";

    }


    @GetMapping("/edit-offerte/")
    public String editSpecialOffer(@PathVariable("offerteId") Integer offerteId, Model model) {
        OfferteSpecialiMod offerte = offSpecialiRepo.findById(offerteId).orElse(null);
        if (offerte == null) {
            return "redirect:/pizzeria/menu";
        }

        model.addAttribute("offerte", offerte);
        return "/pizzeria/edit-offerte";
    }

    @PostMapping("/edit-offerte")
    public String updateSpecialOffer(@PathVariable("offerteId") Integer offerteId, @Valid @ModelAttribute("offerte") OfferteSpecialiMod offerte,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzeria/edit-offerte";
        }

        offSpecialiRepo.save(offerte);
        return "redirect:/pizzeria/dettaglio/" + offerte.getPizza().getId();
    }
}
