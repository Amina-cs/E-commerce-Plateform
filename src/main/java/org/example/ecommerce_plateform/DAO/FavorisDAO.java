package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.favoris;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FavorisDAO {

    public void save(favoris fav) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(fav);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public favoris findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(favoris.class, id);
        }
    }

    public List<favoris> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from favoris", favoris.class).list();
        }
    }

    public void update(favoris fav) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(fav);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(favoris fav) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(fav);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // 🔥 récupérer les favoris d’un utilisateur
    public List<favoris> findByUtilisateurId(int utilisateurId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from favoris f where f.utilisateur.idUtilisateur = :uid", favoris.class)
                    .setParameter("uid", utilisateurId)
                    .list();
        }
    }
}