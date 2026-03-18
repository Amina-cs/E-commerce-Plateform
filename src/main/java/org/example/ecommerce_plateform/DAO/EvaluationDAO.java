package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.evaluation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class EvaluationDAO {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");

    public void save(evaluation eval) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(eval);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public evaluation findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(evaluation.class, id);
        } finally {
            em.close();
        }
    }

    public List<evaluation> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM evaluation", evaluation.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(evaluation eval) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(eval);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            evaluation eval = em.find(evaluation.class, id);
            if (eval != null) {
                em.remove(eval);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}