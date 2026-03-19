package org.example.ecommerce_plateform.DAO;

import jakarta.enterprise.context.ApplicationScoped;
import org.example.ecommerce_plateform.entities.produit;
import org.example.ecommerce_plateform.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

@ApplicationScoped
public class ProduitDAO {
    public void save(produit produit) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(produit);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public produit findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(produit.class, id);
        }
    }

    public List<produit> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from produit", produit.class).list();
        }
    }

    public void update(produit produit) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(produit);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void delete(produit produit) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.remove(produit);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Produits par catégorie
    public List<produit> findByCategorie(int idCategorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select p from produit p join p.categories c where c.idCategorie = :id",
                            produit.class)
                    .setParameter("id", idCategorie)
                    .list();
        }
    }

    // Recherche
    public List<produit> searchByKeyword(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from produit p where p.nomProduit like :kw or p.descriptionProduit like :kw",
                            produit.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .list();
        }
    }

    // Produits disponibles
    public List<produit> findAvailableProduits() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from produit p where p.stock > 0",
                            produit.class)
                    .list();
        }
    }

    List<produit> findTopRated(){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "select p from produit p left join p.evaluations e " +
                            "group by p.idProduit " +
                            "order by avg(e.nbrEtoile) desc",
                    produit.class
            ).list();

        }
    }
}
