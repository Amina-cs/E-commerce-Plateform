package org.example.ecommerce_plateform.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("1")
public class client extends utilisateur {

    @Embedded
    private adresse adresse;



    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "produitsFavoris",
            joinColumns = @JoinColumn(name = "idUtilisateur"),
            inverseJoinColumns = @JoinColumn(name = "idProduit")
    )
    private Set<produit> produitsFavoris = new HashSet<produit>();



    public Set<produit> getProduitsFavoris() {
        return produitsFavoris;
    }
    public void setProduitsFavoris(Set<produit> produitsFavoris) {
        this.produitsFavoris = produitsFavoris;
    }

    public adresse getAdresse() {
        return adresse;
    }

    public void setAdresse( adresse adresse ) {
        this.adresse = adresse;
    }












}
