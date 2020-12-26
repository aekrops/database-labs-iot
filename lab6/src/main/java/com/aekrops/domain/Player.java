package com.aekrops.domain;


import javax.persistence.*;

@Table(name = "player")
@Entity
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "team_id", referencedColumnName = "id", nullable = false)
  private Team team;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  public Player() {

  }

  public Player(Team team, String name, Integer age) {
    this(-1, team, name, age);
  }

  public Player(Integer id, Team team, String name, Integer age) {
    this.id = id;
    this.team = team;
    this.name = name;
    this.age = age;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
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
    return "\n\nid= " + id + ", \nteam= " + team +
        ", \nname= " + name + ", \nage= " + age;
  }
}
