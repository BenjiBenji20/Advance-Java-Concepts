package com.demo.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Objects;

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

            // simple select all query
            Query a = session.createQuery("from Patient");
            List<Patient> patients = (List<Patient>) a.list();

            for(Patient pat : patients) {
                System.out.println(pat);
            }


            // HQL with parameter or prepared statement
            int id = 1;

            Query q = session.createQuery("select name, gender from Doctor where id = :id");
            q.setParameter("id", id);

            Object[] o = (Object[]) q.uniqueResult();

            for(Object p : o) {
                System.out.println(p);
            }

            // using SQL aggregate functions in HQL
            Query b = session.createQuery("SELECT COUNT(*) from Patient");
            Long cnt = (Long) b.uniqueResult();

            System.out.println("The count is: " + cnt);

            // using delete statement
            Query c = session.createQuery("DELETE FROM Patient WHERE id = :id");
            c.setParameter("id", id);

            int obj = c.executeUpdate();

            System.out.println("Rows affected: " + obj);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error found: " + e.getMessage());
        }
    }
}
