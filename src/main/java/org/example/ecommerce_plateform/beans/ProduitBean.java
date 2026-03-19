package org.example.ecommerce_plateform.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.DAO.ProduitDAO;
import org.example.ecommerce_plateform.entities.produit;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProduitBean implements Serializable {

    @Inject
    private ProduitDAO produitDAO;

    private List<produit> produits;
    private int selectedCategorieId; // pour filtrer

    @PostConstruct
    public void init() {
        produits = produitDAO.findAll();
    }

    public void filtrerParCategorie() {
        if (selectedCategorieId > 0) {
            produits = produitDAO.findByCategorie(selectedCategorieId);
        } else {
            produits = produitDAO.findAll();
        }
    }

    // getters & setters
    public List<produit> getProduits() { return produits; }
    public int getSelectedCategorieId() { return selectedCategorieId; }
    public void setSelectedCategorieId(int id) { this.selectedCategorieId = id; }
}