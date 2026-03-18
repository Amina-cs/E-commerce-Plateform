package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.*;
import org.example.ecommerce_plateform.entities.*;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CommandeService {

    private CommandeDAO commandeDAO = new CommandeDAO();
    private ProduitDAO produitDAO = new ProduitDAO();

    public commande passerCommande(client client) {

        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            // 🔹 récupérer panier
            panier panier = session.createQuery(
                            "from panier p where p.client.idUtilisateur = :id",
                            panier.class)
                    .setParameter("id", client.getIdUtilisateur())
                    .uniqueResult();

            if (panier == null || panier.getLignes().isEmpty()) {
                throw new RuntimeException("Panier vide");
            }

            // 🔹 créer commande
            commande commande = new commande();
            commande.setClient(client);
            commande.setDate(new Date());

            List<ligneCommande> lignesCommande = new ArrayList<>();
            double total = 0;

            // 🔥 parcourir panier
            for (lignePanier lp : panier.getLignes()) {

                produit produit = session.get(produit.class, lp.getProduit().getIdProduit());

                int quantite = lp.getQuantite();

                // 🔥 vérification stock
                if (produit.getStock() < quantite) {
                    throw new RuntimeException(
                            "Stock insuffisant pour : " + produit.getNomProduit()
                    );
                }

                // 🔹 créer ligne commande
                ligneCommande lc = new ligneCommande();
                lc.setProduit(produit);
                lc.setQuantite(quantite);
                lc.setPrixPartiel(produit.getPrixProduit());
                lc.setCommande(commande);

                lignesCommande.add(lc);

                // 🔹 calcul total
                total += produit.getPrixProduit() * quantite;

                // 🔥 mise à jour stock
                produit.setStock(produit.getStock() - quantite);
                session.merge(produit);
            }

            // 🔹 finaliser commande
            commande.setLignes(lignesCommande);
            commande.setPrixTotal(total);
            commande.setStatus(statusCommande.confirme);

            session.persist(commande);

            // 🔥 vider panier
            panier.getLignes().clear();
            session.merge(panier);

            // 🔥 commit global
            tx.commit();

            return commande;

        } catch (Exception e) {

            if (tx != null) tx.rollback();
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la commande");

        }
    }
}
