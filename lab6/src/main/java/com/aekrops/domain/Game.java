package com.aekrops.domain;

import javax.persistence.*;

@Table(name = "game")
@Entity
public class Game {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "season")
  private String season;

  @Column(name = "guests_team")
  private String guestsTeam;

  @Column(name = "hosts_team")
  private String hostsTeam;

  @Column(name = "tournament")
  private String tournament;

  @Column(name = "referee")
  private String referee;

  @Column(name = "stadium")
  private String stadium;

  @Column(name = "match_date")
  private String matchDate;

  public Game() {

  }

  public Game(String season, String guests_team, String hosts_team, String tournament,
              String referee, String stadium, String match_date) {
    this(-1, season, guests_team, hosts_team, tournament, referee, stadium, match_date);
  }

  public Game(Integer id, String season, String guestsTeam, String hostsTeam, String tournament,
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

  public void setGuestsTeam(String guests_team) {
    this.guestsTeam = guests_team;
  }

  public String getHostsTeam() {
    return hostsTeam;
  }

  public void setHostsTeam(String hosts_team) {
    this.hostsTeam = hosts_team;
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

  @Override
  public String toString() {
    return "\n\n[ id= " + id + ", \nseason= " + season + ", \nguestsTeam= " +
        guestsTeam + ", \nhostsTeam= " + hostsTeam + ", \ntournament= " + tournament +
        ", \nreferee= " + referee + ", \nstadium= " + stadium + ", \nmatchDate= " +
        matchDate + " ]";
  }
}
