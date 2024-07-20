package it.ludo.pizzeria.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class IngredientiMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Il nome non può essere vuoto")
    private String nome;

    @ManyToMany(mappedBy = "ingredienti")
    private List<PizzaMod> pizze;

    // getter e setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PizzaMod> getPizze() {
        return pizze;
    }

    public void setPizze(List<PizzaMod> pizze) {
        this.pizze = pizze;
    }
}
