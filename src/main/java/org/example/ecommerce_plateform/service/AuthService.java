package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.UtilisateurDAO;
import org.example.ecommerce_plateform.entities.utilisateur;

public class AuthService {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public utilisateur login(String email, String password) {

        utilisateur user = utilisateurDAO.findByEmail(email);

        if (user == null) {
            return null;
        }

        // comparaison du mot de passe
        if (!user.getPwd().equals(password)) {
            return null;
        }

        return user;
    }

    // logout dans les servlet
    //request.getSession().invalidate();
}