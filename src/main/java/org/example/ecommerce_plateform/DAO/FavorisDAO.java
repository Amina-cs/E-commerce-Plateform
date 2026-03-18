package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.favoris;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class FavorisDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");

    public void save(favoris fav) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fav);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public favoris findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(favoris.class, id);
        } finally {
            em.close();
        }
    }

    public List<favoris> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM favoris", favoris.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(favoris fav) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(fav);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            favoris fav = em.find(favoris.class, id);
            if (fav != null) {
                em.remove(fav);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // récupérer les favoris d'un utilisateur
    public List<favoris> findByUtilisateurId(int utilisateurId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "FROM favoris f WHERE f.utilisateur.idUtilisateur = :uid", favoris.class)
                    .setParameter("uid", utilisateurId)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}