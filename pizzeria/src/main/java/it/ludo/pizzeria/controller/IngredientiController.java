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

import it.ludo.pizzeria.model.IngredientiMod;
import it.ludo.pizzeria.model.PizzaMod;
import it.ludo.pizzeria.repository.IngredientiRepo;
import it.ludo.pizzeria.repository.PizzaRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {
    @Autowired
    private IngredientiRepo ingredientiRepo;
    @Autowired
    private PizzaRepo pizzaRepo;


    @GetMapping
    public String getListaIngredienti(Model model) {
        List<IngredientiMod> listaIngredienti = ingredientiRepo.findAll();
        List<PizzaMod> listaPizze = pizzaRepo.findAll();
        model.addAttribute("listaIngredienti", listaIngredienti);
        model.addAttribute("listaPizze", listaPizze);
        return "ingredienti_folder/listaIngredienti";
    }

    @GetMapping("/create-ingredienti")
    public String createIngrediente(Model model) {
        model.addAttribute("ingredienti", new IngredientiMod());
        return "ingredienti_folder/create-ingredienti";
    }

    @PostMapping("/create-ingredienti")
    public String store(@Valid @ModelAttribute("ingredienti") IngredientiMod ingredientiForm,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti_folder/create-ingredienti";
        }

        ingredientiRepo.save(ingredientiForm);
        return "redirect:/ingredienti";
    }

    @GetMapping("/edit-ingredienti/{ingredientiId}")
    public String editIngrediente(@PathVariable("ingredientiId") Integer ingredientiId, Model model) {
        IngredientiMod ingredienti = ingredientiRepo.findById(ingredientiId).orElse(null);
        if (ingredienti == null) {
            return "redirect:/ingredienti";
        }

        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti_folder/edit-ingredienti";
    }

    @PostMapping("/edit-ingredienti/{ingredientiId}")
    public String updateIngrediente(@PathVariable("ingredientiId") Integer ingredientiId,
            @Valid @ModelAttribute("ingredienti") IngredientiMod ingredienti,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti_folder/edit-ingredienti";
        }

        ingredienti.setId(ingredientiId);
        ingredientiRepo.save(ingredienti);
        return "redirect:/ingredienti";
    }

    @GetMapping("/delete-ingredienti/{ingredientiId}")
    public String deleteIngrediente(@PathVariable("ingredientiId") Integer ingredientiId) {
        ingredientiRepo.deleteById(ingredientiId);
        return "redirect:/ingredienti";
    }
}
