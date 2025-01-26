package com.demo.fetches;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {

    public static void main(String[] args) {
        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Order.class);

        try(
            ServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                    .configure().build();

            SessionFactory sf = config.buildSessionFactory(registry);
            Session session = sf.openSession();
        ) {
            Transaction transaction = session.beginTransaction();

            Customer c1 = new Customer();
            c1.setCustomerName("Benji D");

            Order o1 = new Order();
            o1.setProductName("T-shirt");

            // establish relationship
            c1.getOrders().add(o1);
            o1.setCostumer(c1);

            session.save(c1);
            session.save(o1);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error found " + e.getMessage());
        }
    }

}
