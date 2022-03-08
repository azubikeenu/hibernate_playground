package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PrimaryKeyDemo {
  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      List<Student> students =
          List.of(
              new Student("Chidimma", "Ohaka", "angel@gmail.com"),
              new Student("Michael", "Enu", "mike@gmail.com"));
      System.out.println("Beginning transaction");
      session.beginTransaction();
      System.out.println("Saving Students");
      students.forEach(session::save);
      session.getTransaction().commit();
      System.out.println("Done");

    } finally {
      sessionFactory.close();
    }
  }
}
