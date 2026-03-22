package org.example.ecommerce_plateform.beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.commande;
import org.example.ecommerce_plateform.service.CommandeService;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class historiqueBean implements Serializable {

    @Inject
    private AuthBean authBean;

    private client client;
    @Inject
    private CommandeService commandeService;


   public List<commande> getCommandes(){
       client client = authBean.getClient();
       if(client == null){
           return null;
       }

        return commandeService.getCommandesParClient(client);
   }
}
