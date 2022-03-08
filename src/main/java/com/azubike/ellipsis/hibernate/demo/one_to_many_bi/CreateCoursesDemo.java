package com.azubike.ellipsis.hibernate.demo.one_to_many_bi;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class CreateCoursesDemo {
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

      final Course course = new Course("Java script for complete beginners");
      instructor.addCourse(course);

      // This saves the courses
      session.save(course);
      session.getTransaction().commit();

      System.out.println("------------DONE----------------------");

    } catch (Exception ex) {
      ex.printStackTrace();
      //System.out.println(ex.getMessage());
    } finally {
      sessionFactory.close();
    }
  }
}
