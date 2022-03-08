package com.azubike.ellipsis.hibernate.demo;

import com.azubike.ellipsis.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.Optional;

public class ReadStudentDemo {

  public static void main(String[] args) {
    SessionFactory sessionFactory =
        new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .buildSessionFactory();

    Session session = sessionFactory.getCurrentSession();

    try {
      Student student = new Student("Daffy", "Duck", "daffy@gmail.com");
      System.out.println("Beginning transaction");
      session.beginTransaction();
      session.save(student);
      System.out.println("Saving the student......");
      session.getTransaction().commit();

      System.out.println("-----------Done---------------");
      // find out the new students id ;
      int savedStudentId = student.getId();
      System.out.println("Saved student id " + savedStudentId);
      session = sessionFactory.getCurrentSession();
      session.beginTransaction();
      Student studentFormDataBase =
          Optional.ofNullable(session.get(Student.class, savedStudentId))
              .orElseThrow(RuntimeException::new);
      System.out.println(studentFormDataBase);
      session.getTransaction().commit();
      System.out.println("-----------Done---------------");

    } finally {
      sessionFactory.close();
    }
  }
}
