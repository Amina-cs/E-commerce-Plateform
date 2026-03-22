package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.categorie;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CategorieDAO {
    public void save(categorie categorie) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(categorie);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public categorie findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(categorie.class, id);
        }
    }

    public List<categorie> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from categorie", categorie.class).list();
        }
    }

    public void update(categorie categorie) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(categorie);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(categorie categorie) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(categorie);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    public int countProduitsByCategorie(int categorieId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "SELECT COUNT(p) FROM produit p " +
                    "WHERE p.categorie.idCategorie = :categorieId";
            Long count = (Long) session.createQuery(hql)
                    .setParameter("categorieId", categorieId)
                    .uniqueResult();
            return count.intValue();
        } finally {
            session.close();
        }
    }
}
