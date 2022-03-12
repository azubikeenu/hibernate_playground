package com.azubike.ellipsis.hibernate.demo.one_to_many_uni;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import com.azubike.ellipsis.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteCourseAndReviewsDemo {
  private static final String MESSAGE = "ID NOT FOUND IN THE DATABASE";

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();

    try (sessionFactory) {
      Session session = sessionFactory.getCurrentSession();

      session.beginTransaction();

      final Course course =
          session
              .createQuery("FROM Course c WHERE c.id = :courseId", Course.class)
              .setParameter("courseId", 8)
              .getSingleResult();

      System.out.println(course.getReviews());

      session.delete(course);

      session.getTransaction().commit();

      System.out.println("------------DONE----------------------");

    } catch (Exception ex) {
      ex.printStackTrace();
      // System.out.println(ex.getMessage());
    }
  }
}
