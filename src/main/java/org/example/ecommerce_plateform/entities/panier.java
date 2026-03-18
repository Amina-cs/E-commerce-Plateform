package org.example.ecommerce_plateform.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.List;

@Entity
public class panier {
    @Id
    @GeneratedValue
    private int idPanier;

    @OneToOne
    private client client;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL)
    private List<lignePanier> lignes;

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public List<lignePanier> getLignes() {
        return lignes;
    }

    public void setLignes(List<lignePanier> lignes) {
        this.lignes = lignes;
    }
}
