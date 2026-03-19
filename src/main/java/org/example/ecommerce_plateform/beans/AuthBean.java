package org.example.ecommerce_plateform.beans;


import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.utilisateur;
import org.example.ecommerce_plateform.service.AuthService;

import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {

    private String loginEmail;
    private String loginPassword;

    // champs signup
    private String nom;
    private String prenom;
    private String registerEmail;
    private String registerPassword;
    private long num;

    private client client;

    private AuthService authService = new AuthService();

    public String login() {
        utilisateur user = authService.login(loginEmail, loginPassword);

        if (user == null) {
            return "login.xhtml?error=true";
        }

        if (user instanceof client) {
            client = (client) user;
            return "acceuil.xhtml?faces-redirect=true";
        }

        // si tu ajoutes admin plus tard
        return "login.xhtml?error=true";
    }

    // SIGN UP
    public String register() {

        try {
            client c = new client();
            c.setNom(nom);
            c.setPrenom(prenom);
            c.setEmail(registerEmail);
            c.setPwd(registerPassword);
            c.setNum(num);

            authService.register(c);

            return "login.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String logout() {
        client = null;

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        return "login.xhtml?faces-redirect=true";
    }

    public String getLoginEmail() {
        return loginEmail;
    }

    public void setLoginEmail(String loginEmail) {
        this.loginEmail = loginEmail;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRegisterEmail() {
        return registerEmail;
    }

    public void setRegisterEmail(String registerEmail) {
        this.registerEmail = registerEmail;
    }

    public String getRegisterPassword() {
        return registerPassword;
    }

    public void setRegisterPassword(String registerPassword) {
        this.registerPassword = registerPassword;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public client getClient() {
        return client;
    }

    public void setClient(client client) {
        this.client = client;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}