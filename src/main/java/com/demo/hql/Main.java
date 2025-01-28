package com.demo.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(Doctor.class)
                .addAnnotatedClass(Patient.class);

        try(
            ServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure().build();

            SessionFactory sf = config.buildSessionFactory(registry);
            Session session = sf.openSession()
        ) {
            Transaction transaction = session.beginTransaction();
            Doctor doc1 = new Doctor();
            doc1.setName("Benji C");
            doc1.setGender("Male");

            Patient pat1 = new Patient();
            pat1.setName("Kate M");

            // establish relationship
            pat1.setDoctor(doc1);
            doc1.getPatients().add(pat1);

            // session.persist(doc1); // save query

            Query q = session.createQuery("from Doctor");
            List<Doctor> doctors = q.list();

            for(Doctor p : doctors) {
                System.out.println(p);
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error found: " + e.getMessage());
        }
    }
}
