package org.example.ecommerce_plateform.DAO;

import org.example.ecommerce_plateform.entities.client;
import org.example.ecommerce_plateform.entities.commande;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CommandeDAO {

    public void save(commande commande) {
        // On ouvre une session mais on laisse le Service gérer la transaction globale
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(commande); // merge est plus sûr que persist si l'objet existe déjà
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public commande findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(commande.class, id);
        }
    }

    public List<commande> findByClient(client c) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct c from commande c " +
                                    "left join fetch c.lignes l " +
                                    "left join fetch l.produit " +
                                    "where c.client = :client order by c.date desc",
                            commande.class)
                    .setParameter("client", c)
                    .list();
        }
    }

    public static int countCommandes() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "SELECT COUNT(c) FROM commande c", int.class)
                    .uniqueResult();
        }
    }
}