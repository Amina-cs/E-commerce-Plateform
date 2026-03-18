package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.PanierDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.lignePanier;
import org.example.ecommerce_plateform.entities.panier;
import org.example.ecommerce_plateform.entities.produit;

import java.util.ArrayList;

public class PanierService {
    private PanierDAO panierDAO = new PanierDAO();

    public void ajouterProduit(client client, produit produit, int quantite) {

        panier panier = panierDAO.findByClient(client.getIdUtilisateur());

        if (panier == null) {
            panier = new panier();
            panier.setClient(client);
            panier.setLignes(new ArrayList<>());
        }

        // vérifier si produit déjà dans panier
        for (lignePanier ligne : panier.getLignes()) {
            if (ligne.getProduit().getIdProduit() == produit.getIdProduit()) {
                ligne.setQuantite(ligne.getQuantite() + quantite);
                panierDAO.update(panier);
                return;
            }
        }

        // sinon ajouter nouvelle ligne
        lignePanier ligne = new lignePanier();
        ligne.setProduit(produit);
        ligne.setQuantite(quantite);
        ligne.setPanier(panier);

        panier.getLignes().add(ligne);

        panierDAO.save(panier);
    }

    public void supprimerProduit(client client, int produitId) {
        panier panier = panierDAO.findByClient(client.getIdUtilisateur());

        panier.getLignes().removeIf(l ->
                l.getProduit().getIdProduit() == produitId
        );

        panierDAO.update(panier);
    }

    public void modifierQuantite(client client, int produitId, int qte) {
        panier panier = panierDAO.findByClient(client.getIdUtilisateur());

        for (lignePanier l : panier.getLignes()) {
            if (l.getProduit().getIdProduit() == produitId) {
                l.setQuantite(qte);
            }
        }

        panierDAO.update(panier);
    }
}
