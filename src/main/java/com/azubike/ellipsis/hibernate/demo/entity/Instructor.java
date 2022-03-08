package com.azubike.ellipsis.hibernate.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Instructor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column private String firstName;
  @Column private String lastName;
  @Column private String email;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "instructor_detail_id")
  private InstructorDetail instructorDetail;

  @OneToMany(
      mappedBy = "instructor",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
  private List<Course> courses;

  public Instructor() {}

  public Instructor(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public void addCourse(Course course) {
    if (courses == null) {
      courses = new ArrayList<>();
    }
    courses.add(course);
    course.setInstructor(this);
  }

  public void removeCourse(Course course) {
    if (courses.size() > 0) {
      // find the course from the list of courses
      final Course foundCourse =
          courses.stream()
              .filter(c -> c.getTitle() == course.getTitle())
              .findAny()
              .orElseThrow(
                  () -> new RuntimeException("Course not found among the list of courses"));
      if (foundCourse != null) {
        courses.remove(foundCourse);
        foundCourse.setInstructor(null);
      }
    }
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

  public InstructorDetail getInstructorDetail() {
    return instructorDetail;
  }

  public void setInstructorDetail(InstructorDetail instructorDetail) {
    this.instructorDetail = instructorDetail;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void setCourses(List<Course> courses) {
    this.courses = courses;
  }

  @Override
  public String toString() {
    return "Instructor{"
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
        + ", instructorDetail="
        + instructorDetail
        + ", courses="
        + courses
        + '}';
  }
}
