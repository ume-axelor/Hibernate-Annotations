package com.app.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        
        Configuration cfg = new Configuration();
        SessionFactory factory = cfg.configure().buildSessionFactory();
        
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();

        Student student = new Student();
        student.setStudentId(1L);
        student.setFirstName("Kaushal");  // Corrected from setLastName
        student.setLastName("Urvil");
        student.setDateOfBirth(java.sql.Date.valueOf("1990-05-15"));

        session.persist(student);
        t.commit();
        System.out.println("Successfully saved");

        factory.close();
        session.close();
    }
}
