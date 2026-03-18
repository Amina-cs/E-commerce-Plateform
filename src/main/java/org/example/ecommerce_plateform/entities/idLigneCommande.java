package org.example.ecommerce_plateform.entities;

import java.io.Serializable;
import java.util.Objects;

public class idLigneCommande implements Serializable {
    private int idCommande;
    private int idProduit;

    public idLigneCommande(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        idLigneCommande that = (idLigneCommande) o;
        return idCommande == that.idCommande && idProduit == that.idProduit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCommande, idProduit);
    }
}
