package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

public class DeleteStudentDemo {

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      session.beginTransaction();
      int studentId = 4;

      System.out.println("----------Deleting the Student ------------");
      Student student =
          Optional.ofNullable(session.get(Student.class, studentId))
              .orElseThrow(() -> new RuntimeException("Student not found in the database"));
      session.delete(student);
      session.getTransaction().commit();

      System.out.println("-----------Done---------------");

    }catch( Exception ex){
      System.out.println(ex.getMessage());
      sessionFactory.close();
    }
    finally {
      sessionFactory.close();
    }
  }
}
