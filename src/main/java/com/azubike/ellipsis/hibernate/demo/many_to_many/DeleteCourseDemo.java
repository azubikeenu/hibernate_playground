package com.azubike.ellipsis.hibernate.demo.many_to_many;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class DeleteCourseDemo {
  private static final String MESSAGE = "ID NOT FOUND IN THE DATABASE";

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      int courseId = 11;

      session.beginTransaction();

      Course course =
          Optional.ofNullable(session.get(Course.class, courseId))
              .orElseThrow(() -> new RuntimeException("No course found with ID" + courseId));

      session.delete(course);

      session.getTransaction().commit();

      System.out.println("------------DONE----------------------");

    } catch (Exception ex) {
      ex.printStackTrace();
      // System.out.println(ex.getMessage());
    } finally {
      sessionFactory.close();
    }
  }
}
