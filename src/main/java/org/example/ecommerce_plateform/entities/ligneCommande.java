package org.example.ecommerce_plateform.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@IdClass(idLigneCommande.class)
public class ligneCommande {
    @Id
    @ManyToOne
    @JoinColumn(name = "idCommande")
    private commande commande;

    @Id
    @ManyToOne
    @JoinColumn(name = "idProduit")
    private produit produit;


    private double prixProduit;

    private int quantite;

    public ligneCommande() {}

    public commande getCommande() {
        return commande;
    }

    public void setCommande(commande commande) {
        this.commande = commande;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixPartiel) {
        this.prixProduit = prixPartiel;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ligneCommande)) return false;
        ligneCommande other = (ligneCommande) o;
        return  commande==(other.getCommande() )&& produit==(other.getProduit());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}
