package it.ludo.pizzeria.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizza")
public class PizzaMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Inserisci il nome della pizza")
    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "Inserisci la descrizione della pizza")
    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "foto", nullable = true)
    private String foto;

    @NotNull(message = "Inserisci il prezzo della pizza")
    @Column(name = "price", nullable = false)
    private double price;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<OfferteSpeciali> offerteSpeciali;

    // Getters and Setters

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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OfferteSpeciali> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OfferteSpeciali> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }

    @Override
    public String toString() {
        return "PizzaMod [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", foto=" + foto + ", price="
                + price + "]";
    }
}