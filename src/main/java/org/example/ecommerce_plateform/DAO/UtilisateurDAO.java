package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.utilisateur;
import org.example.ecommerce_plateform.util.HashUtil;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UtilisateurDAO {
    public void save(utilisateur user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            session.persist(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public utilisateur findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(utilisateur.class, id);
        }
    }

    public List<utilisateur> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from utilisateur", utilisateur.class).list();
        }
    }

    public void update(utilisateur user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(utilisateur user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public utilisateur findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from utilisateur where email = :email", utilisateur.class)
                    .setParameter("email", email)
                    .uniqueResult();
        }
    }

    public static int countClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT COUNT(c) FROM client c", int.class)
                    .uniqueResult();
        }
    }


}
