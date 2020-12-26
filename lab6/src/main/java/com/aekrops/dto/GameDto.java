package com.aekrops.dto;

public class GameDto {

  private Integer id;

  private String season;

  private String guestsTeam;

  private String hostsTeam;

  private String tournament;

  private String referee;

  private String stadium;

  private String matchDate;

  public GameDto(Integer id, String season, String guestsTeam, String hostsTeam, String tournament,
                 String referee, String stadium, String matchDate) {
    this.id = id;
    this.season = season;
    this.guestsTeam = guestsTeam;
    this.hostsTeam = hostsTeam;
    this.tournament = tournament;
    this.referee = referee;
    this.stadium = stadium;
    this.matchDate = matchDate;
  }

  public GameDto() {

  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public String getGuestsTeam() {
    return guestsTeam;
  }

  public void setGuestsTeam(String guestsTeam) {
    this.guestsTeam = guestsTeam;
  }

  public String getHostsTeam() {
    return hostsTeam;
  }

  public void setHostsTeam(String hostsTeam) {
    this.hostsTeam = hostsTeam;
  }

  public String getTournament() {
    return tournament;
  }

  public void setTournament(String tournament) {
    this.tournament = tournament;
  }

  public String getReferee() {
    return referee;
  }

  public void setReferee(String referee) {
    this.referee = referee;
  }

  public String getStadium() {
    return stadium;
  }

  public void setStadium(String stadium) {
    this.stadium = stadium;
  }

  public String getMatchDate() {
    return matchDate;
  }

  public void setMatchDate(String matchDate) {
    this.matchDate = matchDate;
  }
}
