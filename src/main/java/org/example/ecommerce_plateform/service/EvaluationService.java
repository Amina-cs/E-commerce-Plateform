package org.example.ecommerce_plateform.service;

import jakarta.transaction.Transactional;
import org.example.ecommerce_plateform.DAO.EvaluationDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.evaluation;
import org.example.ecommerce_plateform.entities.produit;

import java.util.List;

public class EvaluationService {

    private EvaluationDAO dao = new EvaluationDAO();

    public List<evaluation> getAll(produit p) {
        return dao.getByProduit(p.getIdProduit());
    }
    public void ajouterEvaluation(client client, produit produit, int note, String commentaire) {

        evaluation e = new evaluation();
        e.setClient(client);
        e.setProduit(produit);
        e.setNbrEtoile(note);
        e.setCommentaire(commentaire);


        dao.save(e);
    }
    @Transactional
    public void supprimerEvaluation(evaluation e) {
        if(e!=null) {
            dao.delete(e);
        }


    }
    public void modifierEvaluation(client client, produit produit, int note,String commentaire) {
        evaluation e = new evaluation();
        e.setClient(client);
        e.setProduit(produit);
        e.setNbrEtoile(note);
        e.setCommentaire(commentaire);
        dao.update(e);
    }
}
