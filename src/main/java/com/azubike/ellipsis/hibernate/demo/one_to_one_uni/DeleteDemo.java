package com.azubike.ellipsis.hibernate.demo.one_to_one_uni;

import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class DeleteDemo {
  private static final String message = "ID NOT FOUND IN THE DATABASE";

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {

      int instructorId = 1;
      System.out.println("----------Deleting Instructor and Instructor detail to the database");
      session.beginTransaction();
      Instructor foundInstructor =
          Optional.ofNullable(session.get(Instructor.class, instructorId))
              .orElseThrow(() -> new RuntimeException(message));

      System.out.println(foundInstructor);

      // This deletes the instructor detail due to cascade type of CASCADE.ALL
      Optional.ofNullable(foundInstructor).ifPresent(session::delete);
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
