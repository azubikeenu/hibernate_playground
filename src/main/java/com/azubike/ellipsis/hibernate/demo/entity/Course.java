package com.azubike.ellipsis.hibernate.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(unique = true)
  private String title;

  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.LAZY)
  @JoinColumn(name = "instructor_id")
  private Instructor instructor;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private List<Review> reviews;

  public Course() {}

  public Course(String title) {
    this.title = title;
  }

  public void addReview(Review review) {
    if (reviews == null) {
      reviews = new ArrayList<>();
    }
    reviews.add(review);

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  @Override
  public String toString() {
    return "Course{"
        + "id="
        + id
        + ", title='"
        + title
        + '\''
        + ", instructor="
        + instructor
        + ", reviews="
        + reviews
        + '}';
  }
}
