package org.example.ecommerce_plateform.beans;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.DAO.ProduitDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.evaluation;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.service.EvaluationService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class produitBean implements Serializable {
    ProduitDAO produitDAO=new ProduitDAO();
    EvaluationService evaluationService=new EvaluationService();
    @Inject
    private AuthBean authBean;
    private client client;

    private List<produit> produits;

    private int nbrTotale;

    private produit product_clicked;

    private int nouvelleNote;

    private String commentaire;

    private  Map<evaluation,Boolean> evaluations;

    public int getNouvelleNote() {
        return nouvelleNote;
    }
    public void setNouvelleNote(int nouvelleNote) {
        this.nouvelleNote = nouvelleNote;
    }
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public List<produit> getProduits() {
        return produits;
    }

    public void setProduits(List<produit> produits) {
        this.produits = produits;
    }

    public ProduitDAO getProduitDAO() {
        return produitDAO;
    }

    public void setProduitDAO(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    private String searchInput;


    @PostConstruct
    public void init() {
        produits = produitDAO.findAll();
        nbrTotale=produits.size();// load from DB
    }


    public void searchQuery(){
        if (searchInput != null && !searchInput.trim().isEmpty()) {
            this.produits = produitDAO.searchByKeyword(searchInput);
        } else {
            this.produits = produitDAO.findAll();
        }
    }

    public void filtrerParCategorie(int catId) {

        produits = produitDAO.findByCategorie(catId);
    }

    public int getNbrTotale(){
        return nbrTotale;
    }

    public String detailsProduit(produit p){
        product_clicked=p;
        return "produit?faces-redirect=true";
    }

    public produit getProduct_clicked() {
        return product_clicked;
    }

    public void setProduct_clicked(produit product_clicked) {
        this.product_clicked = product_clicked;
    }

    public void setNbrTotale(int nbrTotale) {
        this.nbrTotale = nbrTotale;
    }
    public String publierEvaluation(){
        client=authBean.getClient();
        if(client==null){
            nouvelleNote=0;
            commentaire=null;
            return "login?faces-redirect=true";
        }
        if(product_clicked!=null&&(nouvelleNote==0||commentaire!=null)){
            evaluationService.ajouterEvaluation(client,product_clicked,nouvelleNote,commentaire);
            nouvelleNote=0;
            commentaire=null;
        }
        return null;
    }


    public Map<evaluation,Boolean> getListEvaluations() {
        client=authBean.getClient();
        List<evaluation> listeEvaluations= evaluationService.getAll(product_clicked);
        evaluations=new HashMap<>();

        if(client==null){
            for(evaluation e:listeEvaluations){
                    evaluations.put(e,false);
            }
            return evaluations;
        }
        for(evaluation e:listeEvaluations){
            if(e.getClient().getIdUtilisateur()==client.getIdUtilisateur()){
                evaluations.put(e,true);
            }else{
                evaluations.put(e,false);
            }
        }
        return evaluations;
    }

    public void getAllProducts(){
        produits=produitDAO.findAll();
    }
    private boolean isSamePerson=false;
    public boolean isIsSamePerson() {
        return isSamePerson;
    }
    public void deleteEvaluation(evaluation evaluation){
        client=authBean.getClient();
        if(client==null){
            return;
        }
        evaluationService.supprimerEvaluation(evaluation);
    }


}
