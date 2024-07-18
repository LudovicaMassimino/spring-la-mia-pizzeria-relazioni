package it.ludo.pizzeria.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerte_speciali")
public class OfferteSpeciali {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    @NotNull(message = "Inserisci la data di inizio dell'offerta speciale")
    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @NotNull(message = "Inserisci la data di fine dell'offerta speciale")
    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    @NotBlank(message = "Inserisci il titolo dell'offerta speciale")
    @Column(name = "titolo", nullable = false)
    private String titolo;

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private PizzaMod pizza;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public PizzaMod getPizza() {
        return pizza;
    }

    public void setPizza(PizzaMod pizza) {
        this.pizza = pizza;
    }
}
