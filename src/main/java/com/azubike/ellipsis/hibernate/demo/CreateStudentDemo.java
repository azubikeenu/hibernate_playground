package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateStudentDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try{
            Student student = new Student("Frank", "Enu", "frank@gmail.com");
            System.out.println("Beginning transaction");
            session.beginTransaction();
            session.save(student);
            System.out.println("Saving the student");
            session.getTransaction().commit();;
            System.out.println("Done");
        }finally {
            sessionFactory.close();
        }

    }

}
