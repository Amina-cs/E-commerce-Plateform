package org.example.ecommerce_plateform.service;

import org.example.ecommerce_plateform.DAO.UtilisateurDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.utilisateur;
import org.example.ecommerce_plateform.util.HashUtil;

public class AuthService {

    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public utilisateur login(String email, String password) {

        utilisateur user = utilisateurDAO.findByEmail(email);

        if (user == null) {
            return null;
        }

        // comparaison du mot de passe
        if (!HashUtil.verifyPassword(password,user.getPwd())) {
            return null;
        }

        return user;
    }

    // SIGN UP
    public void register(client c) {

        // vérifier si email existe
        utilisateur existing = utilisateurDAO.findByEmail(c.getEmail());

        if (existing != null) {
            throw new RuntimeException("Email déjà utilisé");
        }
        c.setPwd(HashUtil.hashPassword(c.getPwd()));
        utilisateurDAO.save(c);
    }

    public void update(utilisateur u) {
        utilisateurDAO.update(u);
    }
}