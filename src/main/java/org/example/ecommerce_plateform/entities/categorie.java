package org.example.ecommerce_plateform.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class categorie {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private int idCategorie;
    @Column(nullable = false, unique = true)
    private String nomCategorie;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "idCategorie"),
            inverseJoinColumns = @JoinColumn(name = "idProduit")
    )
    private Set<produit> liste_produits = new HashSet<>();


    public categorie(){}

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public Set<produit> getListe_produits() {
        return liste_produits;
    }

    public void setListe_produits(Set<produit> liste_produits) {
        this.liste_produits = liste_produits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        categorie category = (categorie) o;
        return  idCategorie==(category.idCategorie);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
