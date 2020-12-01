package com.aekrops.model.entity;

import javax.persistence.*;

@Table(name = "team")
@Entity
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "team_statistic_id", referencedColumnName = "id")
  private TeamStatistic teamStatistic;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "coach_id", referencedColumnName = "id")
  private Coach coach;

  public Team() {

  }

  public Team(String name, TeamStatistic teamStatistic, Coach coach) {
    this(-1, name, teamStatistic, coach);
  }

  public Team(Integer id, String name, TeamStatistic teamStatistic, Coach coach) {
    this.id = id;
    this.name = name;
    this.teamStatistic = teamStatistic;
    this.coach = coach;
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

  public TeamStatistic getTeamStatistic() {
    return teamStatistic;
  }

  public void setTeamStatistic(TeamStatistic teamStatistic) {
    this.teamStatistic = teamStatistic;
  }

  public Coach getCoach() {
    return coach;
  }

  public void setCoach(Coach coach) {
    this.coach = coach;
  }

  @Override
  public String toString() {
    return "\n\n[ id= " + id + ", \nname= " + name +
        ", \nteam_statistic= " + teamStatistic +
        ", \ncoach_id= " + coach + " ]";
  }
}

