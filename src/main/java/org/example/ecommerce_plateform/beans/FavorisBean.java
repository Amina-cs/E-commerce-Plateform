package org.example.ecommerce_plateform.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.service.FavorisService;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class FavorisBean implements Serializable {

    @Inject
    private AuthBean authBean;
    private List<produit> liste;

    private FavorisService service = new FavorisService();

    public void ajouterFavori(produit p) {
        service.ajouter(authBean.getClient(), p);
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    public List<produit> getListe() {
        return liste;
    }

    public void setListe(List<produit> liste) {
        this.liste = liste;
    }

    public FavorisService getService() {
        return service;
    }

    public void setService(FavorisService service) {
        this.service = service;
    }
}