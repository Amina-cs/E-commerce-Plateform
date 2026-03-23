package org.example.ecommerce_plateform.beans;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.DAO.CategorieDAO;
import org.example.ecommerce_plateform.entities.categorie;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class CategorieAdminBean implements Serializable {

    private CategorieDAO categorieDAO = new CategorieDAO();

    private categorie categorie = new categorie();
    private List<categorie> categories;

    @PostConstruct
    public void init() {
        categories = categorieDAO.findAll();
    }

    public String ajouterCategorie() {
        categorieDAO.save(categorie);
        return "categories?faces-redirect=true";
    }

    public void supprimerCategorie(categorie c) {
        categorieDAO.delete(c);
        categories = categorieDAO.findAll();
    }

    public String editCategorie(categorie c) {
        this.categorie = c;
        return "editCategorie?faces-redirect=true";
    }

    public String updateCategorie() {
        categorieDAO.update(categorie);
        return "categories?faces-redirect=true";
    }

    public CategorieDAO getCategorieDAO() {
        return categorieDAO;
    }

    public void setCategorieDAO(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }

    public categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(categorie categorie) {
        this.categorie = categorie;
    }

    public List<categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<categorie> categories) {
        this.categories = categories;
    }
}