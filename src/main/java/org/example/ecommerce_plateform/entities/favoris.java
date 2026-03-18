package org.example.ecommerce_plateform.entities;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class favoris {
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
    private int idFavoris;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private produit produit;

    public favoris(){}

    public int getIdFavoris() {
        return idFavoris;
    }
    public void setIdFavoris(int idFavoris) {
        this.idFavoris = idFavoris;
    }
    public utilisateur getUtilisateur() { return utilisateur; }
    public void setUtilisateur(utilisateur utilisateur) { this.utilisateur = utilisateur;}
    public produit getProduit() { return produit; }
    public void setProduit(produit produit) { this.produit = produit; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof favoris)) return false;
        favoris other = (favoris) o;
        return  idFavoris==(other.getIdFavoris());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
