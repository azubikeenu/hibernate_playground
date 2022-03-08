package com.azubike.ellipsis.hibernate.demo.one_to_one_bi;

import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class GetInstructorDetailDemo {
  private static String errorMessage = "ID NOT FOUND IN THE DATABASE";

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {

      System.out.println(
          "----------Getting an Instructor from their instructor detail <Bidirectional Relationship>-----------------");

      session.beginTransaction();
      // This saves the instructor detail due to cascade type of CASCADE.ALL
      final long instructorDetailId = 2;
      InstructorDetail instructorDetail =
          Optional.ofNullable(session.get(InstructorDetail.class, instructorDetailId))
              .orElseThrow(() -> new RuntimeException(errorMessage));
      System.out.println("The instructorDetail is " + instructorDetail);

      System.out.println("The associated instructor is " + instructorDetail.getInstructor());

      session.getTransaction().commit();

      System.out.println("------------DONE----------------------");

    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      session.close();
      sessionFactory.close();
    }
  }
}
