package com.demo.mapping.relationship;

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
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class);

        try(
                ServiceRegistry registry =
                        new StandardServiceRegistryBuilder()
                            .configure()
                            .build();

                SessionFactory sf = config.buildSessionFactory(registry);
                Session session = sf.openSession();
        ) {
            Transaction transaction = session.beginTransaction();

            Laptop laptop1 = new Laptop();
            laptop1.setBrand("MSI");
            laptop1.setUnit("BM51");

            StudentName name1 = new StudentName();
            name1.setFirstName("Benji");
            name1.setLastName("Canones");

            Student student1 = new Student();
            student1.setName(name1);

            // establish relationship
            laptop1.setStudent(student1);
            student1.getLaptops().add(laptop1);

            session.save(student1); // save student
            session.save(laptop1); // save laptop

            transaction.commit();
        }
        catch (Exception e) {
            System.out.println("Error found" + e.getMessage());
        }
    }
}
