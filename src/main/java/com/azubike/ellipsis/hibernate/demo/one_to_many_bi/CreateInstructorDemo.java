package com.azubike.ellipsis.hibernate.demo.one_to_many_bi;

import com.azubike.ellipsis.hibernate.demo.entity.Course;
import com.azubike.ellipsis.hibernate.demo.entity.Instructor;
import com.azubike.ellipsis.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CreateInstructorDemo {

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
      Instructor instructor = new Instructor("Stanley", "Kanu", "stanley@gmail.com");
      InstructorDetail instructorDetail =
          new InstructorDetail("www.youtube.com/kanu", "Cycling");
      instructor.setInstructorDetail(instructorDetail);

      System.out.println("----------Saving Instructor and Instructor detail to the database");

      session.beginTransaction();
      // This saves the instructor detail due to cascade type of CASCADE.ALL
      session.save(instructor);

      session.getTransaction().commit();

      System.out.println("------------DONE----------------------");

    } finally {
      sessionFactory.close();
    }
  }
}
