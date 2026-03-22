package org.example.ecommerce_plateform.beans;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Id;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.service.FavorisService;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Named
@RequestScoped
public class favorisBean implements Serializable {
    @Inject
    private AuthBean authBean;
    private client client;
    private FavorisService favorisService = new FavorisService();

    private Set<produit> list;
    public Set<produit> getList() {
        return list;
    }

    public void setList(Set<produit> list) {
        this.list = list;
    }



    public favorisBean() {}
    @PostConstruct
    public void init(){
        if (authBean != null) {
            this.client = authBean.getClient();
            if (this.client != null) {
                this.list = this.client.getProduitsFavoris();
            }
        }
    }

    public void supprimer(produit f) {
        if (client != null && f != null) {
            favorisService.supprimer(client, f);
            list.remove(f);
        }    }

    public void ajouterAuxFavoris(produit f) {
        if (client != null && f != null) {
            favorisService.ajouter(client, f);
            list.add(f);
        }    }

    public String toggle(produit f) {
        client=authBean.getClient();
        list=client.getProduitsFavoris();
        if(client==null){
            return "login?faces-redirect=true";
        }
        if ( f != null) {
            if(client.getProduitsFavoris().contains(f)) {
                supprimer(f);

            }else {
                ajouterAuxFavoris(f);

            }
        }
        return null;
    }

    public boolean estFavori(produit f) {
        client=authBean.getClient();
        if (client == null || f == null)
            return false;
        return list.contains(f);
    }
}
