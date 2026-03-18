package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.*;
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

    // Ajouter un produit à une catégorie
    public void addProduitToCategorie(int idCategorie, produit prod) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            categorie cat = session.get(categorie.class, idCategorie);
            if (cat != null) {
                cat.getListe_produits().add(prod);
                prod.getCategories().add(cat); // Maintenir la cohérence bidirectionnelle
                session.merge(cat);
                session.merge(prod);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Supprimer un produit d'une catégorie
    public void removeProduitFromCategorie(int idCategorie, produit prod) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            categorie cat = session.get(categorie.class, idCategorie);
            if (cat != null) {
                cat.getListe_produits().remove(prod);
                prod.getCategories().remove(cat); // Maintenir la cohérence bidirectionnelle
                session.merge(cat);
                session.merge(prod);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}
