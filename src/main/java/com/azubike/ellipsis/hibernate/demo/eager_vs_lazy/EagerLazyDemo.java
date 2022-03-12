package com.azubike.ellipsis.hibernate.demo.eager_vs_lazy;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class EagerLazyDemo {
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
      int instructorId = 9;

      session.beginTransaction();
      final Instructor instructor =
          Optional.ofNullable(session.get(Instructor.class, instructorId))
              .orElseThrow(() -> new RuntimeException(MESSAGE));

      System.out.println("The found instructor is " + instructor);

      System.out.println("The courses for the given instructor is " + instructor.getCourses());

      session.getTransaction().commit();

      session.close();

      System.out.println("The courses for the given instructor is " + instructor.getCourses());

      System.out.println("------------DONE----------------------");

    } catch (Exception ex) {
      ex.printStackTrace();
    } finally {
      session.close();
      sessionFactory.close();
    }
  }
}
