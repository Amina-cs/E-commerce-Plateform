package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.ligneCommande;
import org.example.ecommerce_plateform.entities.idLigneCommande;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LigneCommandeDAO {

    // Sauvegarder une ligne de commande
    public void save(ligneCommande ligne) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(ligne);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Mettre à jour une ligne de commande
    public void update(ligneCommande ligne) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(ligne);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Supprimer une ligne de commande
    public void delete(ligneCommande ligne) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(session.contains(ligne) ? ligne : session.merge(ligne));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Chercher par clé composite
    public ligneCommande findById(int idCommande, int idProduit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            idLigneCommande id = new idLigneCommande(idCommande, idProduit);
            return session.get(ligneCommande.class, id);
        }
    }

    // Liste des lignes d'une commande
    public List<ligneCommande> findByCommande(int idCommande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from ligneCommande lc where lc.commande.idCommande = :cid", ligneCommande.class)
                    .setParameter("cid", idCommande)
                    .list();
        }
    }

    // Liste de toutes les lignes
    public List<ligneCommande> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ligneCommande", ligneCommande.class).list();
        }
    }
}