package org.example.ecommerce_plateform.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.service.PanierService;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

@Named
@SessionScoped
public class PanierBean implements Serializable {

    private PanierService panierService = new PanierService();

    @Inject
    private AuthBean authBean;

    // récupérer les produits
    public Set<Map.Entry<produit, Integer>> getItems() {
        return panierService.getPanier().entrySet();
    }

    public void ajouterProduit(produit p) {
        panierService.ajouterProduit(p, 1);
    }

    public void supprimerProduit(produit p) {
        panierService.supprimerProduit(p);
    }

    public void modifierQuantite(produit p, int qte) {
        panierService.modifierQuantite(p, qte);
    }

    public double getTotal() {
        return panierService.getTotal();
    }

    public Map<produit, Integer> getPanierMap() {
        return panierService.getPanier();
    }
}