package org.example.ecommerce_plateform.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.service.EvaluationService;

@Named
@RequestScoped
public class EvaluationBean {

    private int note;
    private String commentaire;

    @Inject
    private AuthBean authBean;

    private EvaluationService service = new EvaluationService();

    public void ajouterEvaluation(produit p) {
        service.ajouterEvaluation(
                authBean.getClient(),
                p,
                note,
                commentaire
        );
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public AuthBean getAuthBean() {
        return authBean;
    }

    public void setAuthBean(AuthBean authBean) {
        this.authBean = authBean;
    }

    public EvaluationService getService() {
        return service;
    }

    public void setService(EvaluationService service) {
        this.service = service;
    }
}