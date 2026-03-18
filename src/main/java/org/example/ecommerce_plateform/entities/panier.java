package org.example.ecommerce_plateform.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.util.Map;

public class panier {

    private client client;
    private Map<produit,Integer> listeProduits;

    public panier() {}

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public Map<produit, Integer> getListeProduits() {
        return listeProduits;
    }

    public void setListeProduits(Map<produit,Integer> listeProduits) {
        this.listeProduits = listeProduits;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof panier)) return false;
        panier other = (panier) o;
        return  client==(other.getClient());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
