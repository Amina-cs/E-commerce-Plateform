package org.example.ecommerce_plateform.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.service.CommandeService;

@Named
@RequestScoped
public class CommandeBean {

    @Inject
    private AuthBean authBean;

    @Inject
    private PanierBean panierBean;

    private CommandeService commandeService = new CommandeService();

    public String passerCommande() {

        commandeService.passerCommande(
                authBean.getClient(),
                panierBean.getPanierMap()
        );

        // vider panier après commande
        panierBean.getPanierMap().clear();

        return "confirmation.xhtml?faces-redirect=true";
    }
}