package it.ludo.pizzeria.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.ludo.pizzeria.model.PizzaMod;
import it.ludo.pizzeria.repository.PizzaRepo;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzeria")
public class PizzaController {

    @Autowired
    private PizzaRepo pizzaRepo;

    @GetMapping("/menu")
    public String pizze(Model model, @RequestParam(name = "nome", required = false) String nome) {
        List<PizzaMod> menu = new ArrayList<>();
        System.out.println(menu);

        if (nome == null || nome.isBlank()) {
            menu = pizzaRepo.findAll();
            System.out.println("Tutte le pizze: " + menu);
        } else {
            menu = pizzaRepo.findByNome(nome);
            System.out.println("Pizze filtrate per nome '" + nome + "': " + menu);
        }

        model.addAttribute("pizze", menu);
        return "pizzeria/index";
    }

    @GetMapping("/dettaglio/{id}")
    public String dettaglioPizze(@PathVariable("id") Integer id, Model model) {
        PizzaMod pizza = pizzaRepo.findById(id).orElse(null);
        if (pizza == null) {
            return "redirect:/pizzeria/menu";
        }

        model.addAttribute("dettaglio", pizza);
        return "pizzeria/dettaglio";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new PizzaMod());

        return "/pizzeria/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") PizzaMod pizza, BindingResult bindingResult, Model model) {
        if (pizza.getPrice() <= 0) {
            bindingResult.rejectValue("price", "error.pizza", "Inserisci il prezzo della pizza");
        }
        if (bindingResult.hasErrors()) {
            return "/pizzeria/create";
        }

        pizzaRepo.save(pizza);
        return "redirect:/pizzeria/menu";
    }

    @GetMapping("edit/{id}")
    public String editPizza(@PathVariable("id") Integer id, Model model) {
        PizzaMod pizza = pizzaRepo.findById(id).orElse(null);
        if (pizza == null) {
            return "redirect:/pizzeria/menu";
        }

        model.addAttribute("pizza", pizza);
        return "/pizzeria/edit";
    }

    @PostMapping("/edit")
    public String updatePizza(@Valid @ModelAttribute("pizza") PizzaMod pizza, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzeria/edit";
        }
        pizzaRepo.save(pizza);
        return "redirect:/pizzeria/menu";
    }

    @GetMapping("/delete/{id}")
    public String deletePizza(@PathVariable("id") Integer id) {
        pizzaRepo.deleteById(id);
        return "redirect:/pizzeria/menu";
    }
}
