package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class StudentWithDateDemo {
  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      String theDateOfBirthStr = "31/12/1998";

      Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
      System.out.println("--------------Creating the student----------------------");
      Student student = new Student("Santa", "Cruz", "santa@gmail.com", theDateOfBirth);
      System.out.println("Beginning transaction");
      session.beginTransaction();
      session.save(student);
      System.out.println("--------------Saving the student----------------------");
      session.getTransaction().commit();
      ;
      System.out.println("-----------------Done------------------------");
    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      sessionFactory.close();
    }
  }
}
