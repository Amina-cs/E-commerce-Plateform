package org.example.ecommerce_plateform.util;

public class HibernateTest {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory().openSession();

        System.out.println("Tables créées avec succès !");
    }
}