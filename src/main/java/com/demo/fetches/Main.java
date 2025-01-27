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

            // Customer 1
            Customer c1 = new Customer();
            c1.setCustomerName("Benji C");

            Order o1 = new Order();
            o1.setProductName("T-shirt");

            Order o2 = new Order();
            o2.setProductName("Cap");

            Order o3 = new Order();
            o3.setProductName("Bike");

            // establish relationship
            c1.getOrders().add(o1);
            c1.getOrders().add(o2);
            c1.getOrders().add(o3);

            o1.setCostumer(c1);
            o2.setCostumer(c1);
            o3.setCostumer(c1);

//            session.save(c1);
//            session.save(o1);

            // Customer 2
            Customer c2 = new Customer();
            c2.setCustomerName("Ronald V");

            Order o4 = new Order();
            o4.setProductName("T-shirt");

            Order o5 = new Order();
            o5.setProductName("Note Book");

            Order o6 = new Order();
            o6.setProductName("Shoes");

            // establish relationship
            c2.getOrders().add(o4);
            c2.getOrders().add(o5);
            c2.getOrders().add(o6);

            o4.setCostumer(c2);
            o5.setCostumer(c2);
            o6.setCostumer(c2);

//            session.save(c2);
//            session.save(o4);

            transaction.commit();

            Customer printableCustomers = session.get(Customer.class, 1);
            Order printableOrders = session.get(Order.class, 1);

            System.out.println("Customers: " + printableCustomers.getCustomerName());
            System.out.println("Order: " + printableOrders.getProductName());
        } catch (Exception e) {
            System.out.println("Error found " + e.getMessage());
        }
    }

}
