package com.aekrops.dto;

import com.aekrops.domain.Coach;
import com.aekrops.domain.TeamStatistic;

public class TeamDto {

  private Integer id;

  private String name;

  private TeamStatistic teamStatistic;

  private Coach coach;

  public TeamDto(Integer id, String name, TeamStatistic teamStatistic, Coach coach) {
    this.id = id;
    this.name = name;
    this.teamStatistic = teamStatistic;
    this.coach = coach;
  }

  public TeamDto() {
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
}
