package it.ludo.pizzeria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.pizzeria.model.PizzaMod;

public interface PizzaRepo extends JpaRepository<PizzaMod, Integer> {

    List<PizzaMod> findByNome(String nome);
}
