package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.CommandeDAO;
import org.example.ecommerce_plateform.DAO.ProduitDAO;
import org.example.ecommerce_plateform.entities.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommandeService {

    private CommandeDAO commandeDAO = new CommandeDAO();
    private ProduitDAO produitDAO = new ProduitDAO();

    public commande passerCommande(client client, Map<produit, Integer> panier) {

        commande commande = new commande();
        commande.setClient(client);
        commande.setDate(new Date());

        List<ligneCommande> lignes = new ArrayList<>();
        double total = 0;

        for (Map.Entry<produit, Integer> entry : panier.entrySet()) {

            produit produit = entry.getKey();
            int quantite = entry.getValue();

            // 🔥 vérifier stock
            if (produit.getStock() < quantite) {
                throw new RuntimeException("Stock insuffisant pour " + produit.getNomProduit());
            }

            // 🔥 créer ligne commande
            ligneCommande ligne = new ligneCommande();
            ligne.setProduit(produit);
            ligne.setQuantite(quantite);
            ligne.setPrixPartiel(produit.getPrixProduit());
            ligne.setCommande(commande);

            lignes.add(ligne);

            // 🔥 calcul total
            total += produit.getPrixProduit() * quantite;

            // 🔥 mise à jour stock
            produit.setStock(produit.getStock() - quantite);
            produitDAO.update(produit);
        }

        commande.setLignes(lignes);
        commande.setPrixTotal(total);

        // 🔥 sauvegarde
        commandeDAO.save(commande);

        return commande;
    }
}