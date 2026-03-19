package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.*;
import org.example.ecommerce_plateform.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommandeService {

    private CommandeDAO commandeDAO = new CommandeDAO();
    private ProduitDAO produitDAO = new ProduitDAO();
    private LigneCommandeDAO ligneCommandeDAO = new LigneCommandeDAO();

    public commande passerCommande(client client, Map<produit, Integer> panier) {

        // Créer la commande
        commande commande = new commande();
        commande.setClient(client);
        commande.setDate(new Date());

        List<ligneCommande> lignes = new ArrayList<>();
        double total = 0;

        // Sauvegarder la commande d’abord pour avoir son id
        commandeDAO.save(commande);

        // Créer et sauvegarder chaque ligne de commande
        for (Map.Entry<produit, Integer> entry : panier.entrySet()) {
            produit produit = entry.getKey();
            int quantite = entry.getValue();

            // vérifier stock
            if (produit.getStock() < quantite) {
                throw new RuntimeException("Stock insuffisant pour " + produit.getNomProduit());
            }

            // créer ligne commande
            ligneCommande ligne = new ligneCommande();
            ligne.setProduit(produit);
            ligne.setQuantite(quantite);
            ligne.setPrixProduit(produit.getPrixProduit());
            ligne.setCommande(commande);
            lignes.add(ligne);

            // Sauvegarde via DAO
            ligneCommandeDAO.save(ligne);

            // calcul total
            total += produit.getPrixProduit() * quantite;

            // mise à jour stock
            produit.setStock(produit.getStock() - quantite);
            produitDAO.update(produit);
        }

        // Mettre à jour le prix total de la commande
        commande.setLignes(lignes);
        commande.setPrixTotal(total);
        commande.setStatus(statusCommande.confirme);

        // sauvegarde
        commandeDAO.save(commande);

        return commande;
    }
}