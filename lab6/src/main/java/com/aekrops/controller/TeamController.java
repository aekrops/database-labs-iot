package com.aekrops.controller;

import com.aekrops.domain.Team;
import com.aekrops.dto.TeamDto;
import com.aekrops.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/teams")
@RestController
public class TeamController {

  private final TeamService teamService;

  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<TeamDto>> getAll() {
    List<Team> teams = teamService.getAll();
    List<TeamDto> teamDtos = new ArrayList<>();
    for (Team team : teams) {
      TeamDto teamDto = new TeamDto(
          team.getId(),
          team.getName(),
          team.getTeamStatistic(),
          team.getCoach()
      );
      teamDtos.add(teamDto);
    }
    return new ResponseEntity<>(teamDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<TeamDto> getById(@PathVariable Integer id) {
    Team team;
    try {
      team = teamService.getById(id);

      TeamDto teamDto = new TeamDto(
          team.getId(),
          team.getName(),
          team.getTeamStatistic(),
          team.getCoach()
      );
      return new ResponseEntity<>(teamDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Team newTeam) {
    teamService.create(newTeam);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TeamDto> update(@PathVariable Integer id, @RequestBody Team team) {
    Team teamOld;
    try {
      teamOld = teamService.getById(id);
      if (teamOld != null) {
        teamService.update(id, team);
        TeamDto teamOldDto = new TeamDto(
            team.getId(),
            team.getName(),
            team.getTeamStatistic(),
            team.getCoach()
        );
        return new ResponseEntity<>(teamOldDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (teamService.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
