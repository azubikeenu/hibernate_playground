package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class QueryStudentDemo {

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();
      String stmt = "from Student";
      List<Student> students = session.createQuery(stmt, Student.class).getResultList();

      System.out.println("------------ FROM CLAUSE ----------------");
      students.forEach(System.out::println);

      List<Student> returnedList =
          session
              .createQuery("from Student s where s.firstName = :firstName ", Student.class)
              .setParameter("firstName", "Chidimma")
              .getResultList();
      System.out.println("------------ WHERE CLAUSE ----------------");

      returnedList.forEach(System.out::println);

      returnedList =
          session
              .createQuery(
                  "from Student s WHERE s.lastName = :lastName OR s.firstName = :firstName",
                  Student.class)
              .setParameter("firstName", "Daffy")
              .setParameter("lastName", "Chidimma")
              .getResultList();
      System.out.println("------------ OR CLAUSE ----------------");

      returnedList.forEach(System.out::println);

      returnedList =
          session
              .createQuery("FROM Student  s WHERE s.email LIKE '%gmail.com'", Student.class)
              .getResultList();

      System.out.println("------------ LIKE CLAUSE ----------------");
      returnedList.forEach(System.out::println);

      session.getTransaction().commit();
      System.out.println("Done");
    } finally {
      sessionFactory.close();
    }
  }
}
