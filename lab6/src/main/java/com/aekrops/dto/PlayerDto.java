package com.aekrops.dto;

import com.aekrops.domain.Team;

public class PlayerDto {

  private Integer id;

  private Team team;

  private String name;

  private Integer age;

  public PlayerDto(Integer id, Team team, String name, Integer age) {
    this.id = id;
    this.team = team;
    this.name = name;
    this.age = age;
  }

  public PlayerDto() {
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
}
