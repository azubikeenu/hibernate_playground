package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class UpdateStudentDemo {

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();
      int studentId = 1;
      System.out.println("----------Updating Students ------------");
      Student student =
          Optional.ofNullable(session.get(Student.class, studentId))
              .orElseThrow(RuntimeException::new);
      student.setFirstName("Jermaine");
      session.getTransaction().commit();
      System.out.println("-----------Done---------------");

      session = sessionFactory.getCurrentSession();
      session.beginTransaction();

      System.out.println("----------Updating  email for all Students ------------");
      session
          .createQuery("UPDATE Student s SET s.email = :email")
          .setParameter("email", "foo@gamil.com")
          .executeUpdate();

      session.getTransaction().commit();

      System.out.println("-----------Done---------------");

    } finally {
      sessionFactory.close();
    }
  }
}
