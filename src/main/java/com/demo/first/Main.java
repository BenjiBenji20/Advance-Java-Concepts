package com.demo.first;

/**
 * Some methods are depricated since from old version
 * but for learning purposes, I will still use them.
 * Following a tutorial.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

        // Session faction
        try (SessionFactory sf = config.buildSessionFactory();
             Session session = sf.openSession();
        ) {
            Transaction transaction = session.beginTransaction();

            MyFriends mf = new MyFriends();
            mf.setId(2);
            mf.setName("Kate");
            mf.setAge(22);
            mf.setGender("Female");

            // save the table or query to the db
            session.save(mf);

            // commit a transaction
            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
