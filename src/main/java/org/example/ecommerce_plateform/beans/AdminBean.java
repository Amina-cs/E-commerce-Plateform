package org.example.ecommerce_plateform.beans;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.DAO.CommandeDAO;
import org.example.ecommerce_plateform.DAO.UtilisateurDAO;

@Named
@RequestScoped
public class AdminBean {

    private int totalClients;
    private int totalCommandes;

    private final UtilisateurDAO UtilisateurDAO = new UtilisateurDAO();
    private final CommandeDAO CommandeDAO = new CommandeDAO();

    @PostConstruct
    public void init() {
        try {
            totalClients = UtilisateurDAO.countClients();
            totalCommandes = CommandeDAO.countCommandes();
        } catch (Exception e) {
            e.printStackTrace(); // 🔴 IMPORTANT pour debug
        }
    }

    public int getTotalClients() {
        return totalClients;
    }

    public int getTotalCommandes() {
        return totalCommandes;
    }
}