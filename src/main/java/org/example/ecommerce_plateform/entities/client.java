package org.example.ecommerce_plateform.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class client extends utilisateur {

    @Embedded
    private adresse adresse;

    @ManyToMany
    @JoinTable(
            name = "produitsFavoris",
            joinColumns = @JoinColumn(name = "idUtilisateur"),
            inverseJoinColumns = @JoinColumn(name = "idProduit")
    )
    private Set<produit> produitsFavoris = new HashSet<produit>();

    public client(){}


    public adresse getAdresse() {
        return adresse;
    }

    public void setVille( adresse adresse ) {
        this.adresse = adresse;
    }












}
