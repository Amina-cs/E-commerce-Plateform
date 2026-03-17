package org.example.ecommerce_plateform.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class client extends utilisateur {

    @Embedded
    private adresse adresse;

    public client(){}


    public adresse getAdresse() {
        return adresse;
    }

    public void setVille( adresse adresse ) {
        this.adresse = adresse;
    }












}
