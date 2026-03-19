package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.EvaluationDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.evaluation;
import org.example.ecommerce_plateform.entities.produit;

public class EvaluationService {

    private EvaluationDAO dao = new EvaluationDAO();

    public void ajouterEvaluation(client client, produit produit, int note, String commentaire) {

        evaluation e = new evaluation();
        e.setUtilisateur(client);
        e.setProduit(produit);
        e.setNbrEtoile(note);
        e.setCommentaire(commentaire);

        dao.save(e);
    }
}