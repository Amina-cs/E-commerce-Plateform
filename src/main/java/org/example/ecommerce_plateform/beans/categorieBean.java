package org.example.ecommerce_plateform.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.DAO.CategorieDAO;
import org.example.ecommerce_plateform.entities.categorie;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class categorieBean implements Serializable {
    private Map<categorie,Integer> categories=new HashMap<categorie,Integer>();

    private int nbrTotale;
    CategorieDAO categorieDAO = new CategorieDAO();
    @PostConstruct
    public void init(){
        nbrTotale=categorieDAO.findAll().size();
        List<categorie> listCategorie = categorieDAO.findAll();
        for(categorie categorie: listCategorie){
            categories.put(categorie, categorieDAO.countProduitsByCategorie(categorie.getIdCategorie()));
        }

    }


    public Map<categorie, Integer> getCategories() {
        return categories;
    }

    public void setCategories(Map<categorie, Integer> categories) {
        this.categories = categories;
    }

    public int getNbrTotale(){
        return nbrTotale;
    }
}
