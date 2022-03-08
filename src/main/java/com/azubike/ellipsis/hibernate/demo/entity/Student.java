package com.azubike.ellipsis.hibernate.demo.entity;

import com.azubike.ellipsis.hibernate.demo.DateUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column private String firstName;
  @Column private String lastName;
  @Column private String email;

  @Column
  @Temporal(TemporalType.DATE)
  private Date dateOfBirth;

  public Student() {}

  public Student(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public Student(String firstName, String lastName, String email, Date dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  @Override
  public String toString() {

    return "Student{"
        + "id="
        + id
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", email='"
        + email
        + '\''+ ", dateOfBirth='"
        + DateUtils.formatDate(dateOfBirth)
        + '\''
        + '}';
  }
}
