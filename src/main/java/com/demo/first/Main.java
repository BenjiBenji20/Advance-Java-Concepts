package com.demo.first;

/**
 * Some methods are depricated since from old version
 * but for learning purposes, I will still use them.
 * Following a tutorial.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");


        // configuring your class to your hibernate.cfg.xml
        /**
         * Configuration class belongs to org.hibernate.cfg.Configuration
         * which configure a connection between your xml file
         * and class.
         * .configure("Pass the name of hibernate xml file").
         */
        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(MyFriends.class);

        try (
             ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
             SessionFactory sf = config.buildSessionFactory(registry);
             Session session = sf.openSession();
        ) {
            Transaction transaction = session.beginTransaction();

            MyFriends mf = new MyFriends();
            mf.setId(3);
            mf.setName("Jaime");
            mf.setAge(23);
            mf.setGender("Male");


            // save the table or query to the db
             session.save(mf);

            // fetching data
            MyFriends mf2 = (MyFriends) session.get(MyFriends.class, 2);

            // commit a transaction
            transaction.commit();

            System.out.println(mf2);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
