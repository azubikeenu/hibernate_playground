package com.azubike.ellipsis.hibernate.demo.eager_vs_lazy;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.Optional;

public class FetchJoinDemo {
  private static final String MESSAGE = "ID NOT FOUND IN THE DATABASE";

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {

      session.beginTransaction();
      final Instructor instructor = session.createQuery("SELECT i from Instructor  i  " +
              "JOIN FETCH  i.courses " +
              "WHERE i.id = :instructorId", Instructor.class).setParameter("instructorId", 9).getSingleResult();

      session.getTransaction().commit();

      session.close();

      System.out.println("The instructor is " + instructor);
      System.out.println("The courses are " + instructor.getCourses());

      System.out.println("------------DONE----------------------");

    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      session.close();
      sessionFactory.close();
    }
  }
}
