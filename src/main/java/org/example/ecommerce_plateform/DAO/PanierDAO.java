package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.panier;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PanierDAO {
    public panier findByClient(int clientId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select p from produit p join p.categorie c where c.idCategorie = :id",
                            panier.class)
                    .setParameter("id", clientId)
                    .uniqueResult();
        }
    }

    public void save(panier panier) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(panier);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    public void update(panier panier) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(panier);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }
}
