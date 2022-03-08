package com.azubike.ellipsis.hibernate.demo.entity;

import javax.persistence.*;

@Entity
public class InstructorDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column private String youtubeChannel;
  @Column private String hobby;

  @OneToOne(
      mappedBy = "instructorDetail",
      cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH, CascadeType.PERSIST})
  private Instructor instructor;

  public InstructorDetail() {}

  public InstructorDetail(String youtubeChannel, String hobby) {
    this.hobby = hobby;
    this.youtubeChannel = youtubeChannel;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getYoutubeChannel() {
    return youtubeChannel;
  }

  public void setYoutubeChannel(String youtubeChannel) {
    this.youtubeChannel = youtubeChannel;
  }

  public String getHobby() {
    return hobby;
  }

  public void setHobby(String hobby) {
    this.hobby = hobby;
  }

  public Instructor getInstructor() {
    return instructor;
  }

  public void setInstructor(Instructor instructor) {
    this.instructor = instructor;
  }

  @Override
  public String toString() {
    return "InstructorDetail{"
        + "id="
        + id
        + ", youtubeChannel='"
        + youtubeChannel
        + '\''
        + ", hobby='"
        + hobby
        + '\''
        + '}';
  }
}
