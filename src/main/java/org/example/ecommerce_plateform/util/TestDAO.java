package org.example.ecommerce_plateform.util;

import org.example.ecommerce_plateform.DAO.UtilisateurDAO;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.utilisateur;

public class TestDAO {
    public static void main(String[] args) {

        UtilisateurDAO dao = new UtilisateurDAO();

        utilisateur user = new client(); // ou Admin
        user.setNom("Test");
        user.setPrenom("User");
        user.setEmail("test@gmail.com");
        user.setPwd("1234");
        user.setNum(123456789);

        dao.save(user);

        utilisateur u = dao.findByEmail("test@gmail.com");
        System.out.println(u.getNom());
    }
}
