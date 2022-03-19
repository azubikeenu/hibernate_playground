package com.azubike.ellipsis.hibernate.demo.many_to_many;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class CreateCourseAndStudentsDemo {
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
      int studentId = 1;

      session.beginTransaction();
      List<Course> courses =
          List.of(
              new Course("Hibernate explained"),
              new Course("functional programming in js"),
              new Course("Introduction to Go Programming language"));
      Student student =
          Optional.ofNullable(session.get(Student.class, studentId))
              .orElseThrow(() -> new RuntimeException("No student found with ID" + studentId));


      //save the courses ;

      courses.forEach(session::save);


      student.setCourses(courses);


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
