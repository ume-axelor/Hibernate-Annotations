package com.app.training;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory factory = cfg.buildSessionFactory();
             Session session = factory.openSession()) {

            Transaction t = session.beginTransaction();

            try {
                Address address1 = new Address();
                address1.setCity("City1");
                address1.setStreet("Street1");
                address1.setZipCode("ZipCode1");

                session.persist(address1);

                Address address2 = new Address();
                address2.setCity("City2");
                address2.setStreet("Street2");
                address2.setZipCode("ZipCode2");

                session.persist(address2);

                Student student1 = new Student();
                student1.setFirstName("Urvil");
                student1.setLastName("Mehta");
                student1.setDateOfBirth(java.sql.Date.valueOf("1990-05-15"));

                student1.setAddress(address1);

                Laptop laptop1 = new Laptop();
                laptop1.setBrand("Brand1");
                
                laptop1.setModel("Model1");

                Laptop laptop2 = new Laptop();
                laptop2.setBrand("Brand2");
                laptop2.setModel("Model2");

                Feature feature1 = new Feature();
                feature1.setFeatureName("Touch Screen");

                Feature feature2 = new Feature();
                feature2.setFeatureName("Backlit Keyboard");

                laptop1.addFeature(feature1);
                laptop1.addFeature(feature2);

                student1.addLaptopItem(laptop1);
                student1.addLaptopItem(laptop2);

                session.persist(student1);

                Student student2 = new Student();
                student2.setFirstName("Another");
                student2.setLastName("Student");
                student2.setDateOfBirth(java.sql.Date.valueOf("1995-10-20"));

                student2.setAddress(address2);

                Laptop laptop3 = new Laptop();
                laptop3.setBrand("Brand3");
                laptop3.setModel("Model3");

                Laptop laptop4 = new Laptop();
                laptop4.setBrand("Brand4");
                laptop4.setModel("Model4");
                
                Feature feature3 = new Feature();
                feature3.setFeatureName("SSD Drive");

                Feature feature4 = new Feature();
                feature4.setFeatureName("High-Resolution Display");

                laptop3.addFeature(feature3);
                laptop3.addFeature(feature4);

                student2.addLaptopItem(laptop3);
                student2.addLaptopItem(laptop4);

                session.persist(student2);

                t.commit();
                System.out.println("Successfully saved");
            } catch (Exception e) {
                if (t != null && t.isActive()) {
                    t.rollback();
                }
                e.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
