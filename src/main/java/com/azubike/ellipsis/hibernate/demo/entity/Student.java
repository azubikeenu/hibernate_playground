package com.azubike.ellipsis.hibernate.demo.entity;

import com.azubike.ellipsis.hibernate.demo.DateUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "course_student",
      joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses;

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

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  public void addCourse(Course course) {
    if (courses == null) {
      courses = new ArrayList<>();
    }
    courses.add(course);
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
        + '\''
        + ", dateOfBirth='"
        + DateUtils.formatDate(dateOfBirth)
        + '\''
        + '}';
  }
}
