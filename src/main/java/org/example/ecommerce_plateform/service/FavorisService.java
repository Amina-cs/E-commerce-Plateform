package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.FavorisDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.favoris;
import org.example.ecommerce_plateform.entities.produit;

public class FavorisService {

    private FavorisDAO dao = new FavorisDAO();

    public void ajouter(client client, produit produit) {
        favoris f = new favoris();
        f.setUtilisateur(client);
        f.setProduit(produit);

        dao.save(f);
    }
}