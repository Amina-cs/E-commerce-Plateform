package org.example.ecommerce_plateform.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class commande {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_id_sequence"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private int idCommande;

    @CreationTimestamp
    private Date date;

    @Enumerated(EnumType.STRING)
    private statusCommande status;

    private double prixTotal;

    private String numSuivie;

    private double prixLivraison;

    private String serviceLivraison;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    client client ;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ligneCommande> lignes= new ArrayList<>();

    public commande() {}

    public void ajouterLigne(ligneCommande ligne) {
        lignes.add(ligne);
        ligne.setCommande(this);
    }

    public double getPrixLivraison() {
        return prixLivraison;
    }

    public void setPrixLivraison(double prixLivraison) {
        this.prixLivraison = prixLivraison;
    }

    public String getNumSuivie() {
        return numSuivie;
    }

    public void setNumSuivie(String numSuivie) {
        this.numSuivie = numSuivie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public statusCommande getStatus() {
        return status;
    }

    public void setStatus(statusCommande status) {
        this.status = status;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getServiceLivraison() {
        return serviceLivraison;
    }

    public void setServiceLivraison(String serviceLivraison) {
        this.serviceLivraison = serviceLivraison;
    }

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public List<ligneCommande> getLignes() {
        return lignes;
    }

    public void setLignes(List<ligneCommande> lignes) {
        this.lignes = lignes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof commande)) return false;
        commande other = (commande) o;
        return  idCommande==(other.getIdCommande());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public String toString(){
        return "client: " + this.client + " lignes :" + lignes;
    }
}