package org.example.ecommerce_plateform.entities;


import jakarta.persistence.Embeddable;

@Embeddable
public class adresse {
    private String ville;
    private String rue;
    private double latitude;
    private double longitude;

    public adresse(){}
    public adresse(String ville, String rue) {
        this.ville = ville;
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

}
