package org.example.ecommerce_plateform.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class lignePanier {

    @Id
    @GeneratedValue
    private int idLigne;

    @ManyToOne
    private produit produit;

    private int quantite;

    @ManyToOne
    private panier panier;

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public panier getPanier() {
        return panier;
    }

    public void setPanier(panier panier) {
        this.panier = panier;
    }
}