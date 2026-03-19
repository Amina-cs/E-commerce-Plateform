package org.example.ecommerce_plateform.DAO;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.ecommerce_plateform.entities.evaluation;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@ApplicationScoped  // Rend le DAO injectable via CDI
public class EvaluationDAO {

    // Sauvegarder une évaluation
    public void save(evaluation eval) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(eval);  // ou session.save(eval)
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Trouver une évaluation par ID
    public evaluation findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(evaluation.class, id);
        }
    }

    // Récupérer toutes les évaluations
    public List<evaluation> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from evaluation", evaluation.class).list();
        }
    }

    // Mettre à jour une évaluation
    public void update(evaluation eval) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(eval);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Supprimer une évaluation
    public void delete(evaluation eval) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(eval);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Récupérer toutes les évaluations d'un produit
    public List<evaluation> getByProduit(int produitId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from evaluation e where e.produit.idProduit = :id",
                            evaluation.class)
                    .setParameter("id", produitId)
                    .list();
        }
    }
}