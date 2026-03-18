package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.commande;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CommandeDAO {
    public void save(commande commande) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(commande);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public commande findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(commande.class, id);
        }
    }

    public List<commande> findByClient(int clientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from commande c where c.client.idUtilisateur = :id",
                            commande.class)
                    .setParameter("id", clientId)
                    .list();
        }
    }
}
