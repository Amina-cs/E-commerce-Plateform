package org.example.ecommerce_plateform.beans;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.service.CommandeService;
import org.example.ecommerce_plateform.service.PanierService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class panierBean implements Serializable {
    @Inject
    private AuthBean authBean;
    private int nbrProduits;

    PanierService panierService=new PanierService();
    @Inject
    private CommandeService commandeService;
    @PostConstruct
            public void init() {
        nbrProduits=(int)  panierService.getPanier().size();
    }



    public String ajouterAuPanier(produit p){
        if (authBean.getClient() == null) {
            return "/login?faces-redirect=true";
        }

        if(!panierService.getPanier().containsKey(p)){
            ++nbrProduits;
        }
        panierService.ajouterProduit(p,1);


        return null;
    }


    public void retirerproduit(produit p){
        panierService.supprimerProduit(p);
        nbrProduits--;
    }
    public void modifierQuantite(produit p,int quantite){
        if(panierService.getPanier().get(p)==0&&quantite<0){
            return;
        }
        panierService.ajouterProduit(p,quantite);
    }

    public Map<produit,Integer> getProduits(){
        return panierService.getPanier();
    }

    public double getTotal(){
        return panierService.getTotal();
    }



    public void setnbrProduits(int nbrProduits){
        this.nbrProduits=nbrProduits;
    }
    public int getNbrProduits(){
        if(authBean.getClient()==null){
            return 0;
        }
        return nbrProduits;
    }

    public String gotoPanier(){
        if(authBean.getClient()==null){
            return "/login?faces-redirect=true";
        }
        return "panier?faces-redirect=true";
    }

    public String commander(){
        try{
            if(panierService.getTotal()==0){
                return null;
            }
            commandeService.commander(panierService.getPanier());
            panierService.getPanier().clear();
            nbrProduits=0;

            return "success?faces-redirect=true";

        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }

}
