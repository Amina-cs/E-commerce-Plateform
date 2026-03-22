package org.example.ecommerce_plateform.service;

import jakarta.transaction.Transactional;
import org.example.ecommerce_plateform.DAO.UtilisateurDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.entities.utilisateur;

import java.util.Set;

public class FavorisService {

    private UtilisateurDAO clientDAO = new UtilisateurDAO();
    @Transactional
    public void ajouter(client client, produit produit) {
        client managedClient =(client) clientDAO.findById(client.getIdUtilisateur());
        managedClient.getProduitsFavoris().add(produit);
        clientDAO.update(managedClient);    }
    @Transactional
    public void supprimer(client client, produit produit) {
        client managedClient = (client)clientDAO.findById(client.getIdUtilisateur());
        managedClient.getProduitsFavoris().remove(produit);
        clientDAO.update(managedClient);    }











}