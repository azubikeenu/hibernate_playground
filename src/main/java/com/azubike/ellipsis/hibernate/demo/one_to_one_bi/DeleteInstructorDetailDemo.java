package com.azubike.ellipsis.hibernate.demo.one_to_one_bi;

import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class DeleteInstructorDetailDemo {
  private static final String MESSAGE = "ID NOT FOUND IN THE DATABASE";

  public static void main(String[] args) {
    final SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    long instructorDetailId = 3;
    try {
      session.beginTransaction();
      final InstructorDetail instructorDetail =
          session.get(InstructorDetail.class, instructorDetailId);
      Optional.of(instructorDetail)
          .ifPresent(
              (inst) -> {
                inst.getInstructor().setInstructorDetail(null);
                session.delete(inst);
              });
      session.getTransaction().commit();
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      session.close();
      sessionFactory.close();
    }
  }
}
