package org.example.ecommerce_plateform.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.DAO.CategorieDAO;
import org.example.ecommerce_plateform.DAO.ProduitDAO;
import org.example.ecommerce_plateform.entities.categorie;
import org.example.ecommerce_plateform.entities.produit;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProduitAdminBean implements Serializable {

    private ProduitDAO produitDAO = new ProduitDAO();
    private CategorieDAO categorieDAO = new CategorieDAO();

    private produit produit = new produit();
    private List<produit> produits;
    private List<categorie> categories;

    @PostConstruct
    public void init() {
        produits = produitDAO.findAll();
        categories = categorieDAO.findAll();
    }

    public String ajouterProduit() {
        produitDAO.save(produit);
        return "produits?faces-redirect=true";
    }

    public void supprimerProduit(produit p) {
        produitDAO.delete(p);
        produits = produitDAO.findAll();
    }

    public String editProduit(produit p) {
        this.produit = p;
        return "editProduit?faces-redirect=true";
    }

    public String updateProduit() {
        produitDAO.update(produit);
        return "produits?faces-redirect=true";
    }

    public ProduitDAO getProduitDAO() {
        return produitDAO;
    }

    public void setProduitDAO(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    public CategorieDAO getCategorieDAO() {
        return categorieDAO;
    }

    public void setCategorieDAO(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public List<produit> getProduits() {
        return produits;
    }

    public void setProduits(List<produit> produits) {
        this.produits = produits;
    }

    public List<categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<categorie> categories) {
        this.categories = categories;
    }
}