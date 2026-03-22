package org.example.ecommerce_plateform.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.ecommerce_plateform.DAO.*;
import org.example.ecommerce_plateform.beans.AuthBean;
import org.example.ecommerce_plateform.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@ApplicationScoped
public class CommandeService {


    private CommandeDAO commandeDAO = new CommandeDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private LigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAO();


    @Inject
    private AuthBean authBean;

    public CommandeService(){}

    @Transactional
    public void commander(Map<produit, Integer> map) {
        client clt = authBean.getClient();
        try {
            // 1. Préparer l'objet Commande
            commande newCmd = new commande();
            newCmd.setClient(clt);
            newCmd.setDate(new Date());
            newCmd.setStatus(statusCommande.confirme);

            double totalGénéral = 0;
            List<ligneCommande> listeLignes = new ArrayList<>();

            // 2. Boucler sur le panier
            for (Map.Entry<produit, Integer> entry : map.entrySet()) {
                produit p = entry.getKey();
                int qte = entry.getValue();

                if (p.getStock() < qte) throw new Exception("Stock épuisé pour " + p.getNomProduit());

                // Créer la ligne
                ligneCommande ligne = new ligneCommande();
                ligne.setProduit(p);
                ligne.setQuantite(qte);
                ligne.setPrixProduit(p.getPrixProduit());
                ligne.setCommande(newCmd); // Lien vital

                listeLignes.add(ligne);
                totalGénéral += (p.getPrixProduit() * qte);

                // Mettre à jour le stock du produit immédiatement
                p.setStock(p.getStock() - qte);
                produitDAO.update(p);
            }

            // 3. Finaliser l'objet
            newCmd.setLignes(listeLignes);
            newCmd.setPrixTotal(totalGénéral);

            // 4. SAUVEGARDE UNIQUE
            System.out.println("Tentative de sauvegarde de la commande pour : " + clt.getNom());
            commandeDAO.save(newCmd);
            System.out.println("Commande enregistrée avec succès !");

        } catch (Exception e) {
            System.err.println("ERREUR LORS DE LA COMMANDE : " + e.getMessage());
            e.printStackTrace();
        }
    }






        public List<commande> getCommandesParClient(client c){
            return commandeDAO.findByClient(c);
        }
    }


