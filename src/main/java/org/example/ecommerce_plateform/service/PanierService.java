package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.entities.produit;

import java.util.HashMap;
import java.util.Map;

public class PanierService {

    private Map<produit, Integer> panier = new HashMap<>();

    // ajouter produit
    public void ajouterProduit(produit p, int quantite) {
        panier.put(p, panier.getOrDefault(p, 0) + quantite);
    }

    // supprimer produit
    public void supprimerProduit(produit p) {
        panier.remove(p);
    }



    // calcul total
    public double getTotal() {
        return panier.entrySet()
                .stream()
                .mapToDouble(e -> e.getKey().getPrixProduit() * e.getValue())
                .sum();
    }



    public Map<produit, Integer> getPanier() {
        return panier;
    }
}