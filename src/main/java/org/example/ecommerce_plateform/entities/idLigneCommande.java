package org.example.ecommerce_plateform.entities;

import java.io.Serializable;
import java.util.Objects;

public class idLigneCommande implements Serializable {
    private int commande;
    private int produit;

    public idLigneCommande(){}

    public idLigneCommande(int commande, int produit) {
        this.commande = commande;
        this.produit = produit;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        idLigneCommande that = (idLigneCommande) o;
        return commande == that.commande && produit == that.produit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commande, produit);
    }
}
