package com.aekrops.domain;

import javax.persistence.*;

@Table(name = "coach")
@Entity
public class Coach {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  public Coach() {

  }

  public Coach(String name, Integer age) {
    this(-1, name, age);
  }

  public Coach(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "\n\n[ id=" + id +
        ",\nname=" + name + ",\nage=" + age + " ]";
  }
}
