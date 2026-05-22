package com.learn.spring.spring_la_mia_pizzeria_relazioni.models;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Inserire un nome valido")
    private String nome;

    @NotBlank(message = "Inserire una descrizione")
    @Lob
    private String description;

    @NotNull(message = "Inserire una prezzo")
    @Min(value = 0, message = "il prezzo non può essere negativo")
    private BigDecimal price;

    @URL(message = "inserire un URL valido")
    @NotNull
    @ColumnDefault("'https://png.pngtree.com/png-vector/20250825/ourlarge/pngtree-delicious-margherita-pizza-illustration-png-image_17178157.webp'")
    private String url_image;

    @OneToMany(mappedBy = "pizza")
    private List<Offerte> offerte;

    public List<Offerte> getOfferte() {
        return offerte;
    }

    public void setOfferte(List<Offerte> offerte) {
        this.offerte = offerte;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f ,\n %s", nome, price, description);
    }
}
