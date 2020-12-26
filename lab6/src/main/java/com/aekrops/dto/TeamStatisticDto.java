package com.aekrops.dto;

public class TeamStatisticDto {

  private Integer id;

  private Integer victories;

  private Integer percentageHitsOnTarget;

  public TeamStatisticDto(Integer id, Integer victories, Integer percentageHitsOnTarget) {
    this.id = id;
    this.victories = victories;
    this.percentageHitsOnTarget = percentageHitsOnTarget;
  }

  public TeamStatisticDto() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getVictories() {
    return victories;
  }

  public void setVictories(Integer victories) {
    this.victories = victories;
  }

  public Integer getPercentageHitsOnTarget() {
    return percentageHitsOnTarget;
  }

  public void setPercentageHitsOnTarget(Integer percentageHitsOnTarget) {
    this.percentageHitsOnTarget = percentageHitsOnTarget;
  }
}
