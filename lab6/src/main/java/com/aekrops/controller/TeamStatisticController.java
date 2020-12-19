package com.aekrops.controller;

import com.aekrops.domain.TeamStatistic;
import com.aekrops.dto.TeamStatisticDto;
import com.aekrops.service.TeamStatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/team_statistics")
@RestController
public class TeamStatisticController {

  private final TeamStatisticService teamStatisticService;

  public TeamStatisticController(TeamStatisticService teamStatisticService) {
    this.teamStatisticService = teamStatisticService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<TeamStatisticDto>> getAll() {
    List<TeamStatistic> teamStatistics = teamStatisticService.getAll();
    List<TeamStatisticDto> teamStatisticDtos = new ArrayList<>();
    for (TeamStatistic teamStatistic : teamStatistics) {
      TeamStatisticDto teamStatisticDto = new TeamStatisticDto(
          teamStatistic.getId(),
          teamStatistic.getVictories(),
          teamStatistic.getPercentageHitsOnTarget()
      );
      teamStatisticDtos.add(teamStatisticDto);
    }
    return new ResponseEntity<>(teamStatisticDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<TeamStatisticDto> getById(@PathVariable Integer id) {
    TeamStatistic teamStatistic;
    try {
      teamStatistic = teamStatisticService.getById(id);

      TeamStatisticDto teamStatisticDto = new TeamStatisticDto(
          teamStatistic.getId(),
          teamStatistic.getVictories(),
          teamStatistic.getPercentageHitsOnTarget()
      );
      return new ResponseEntity<>(teamStatisticDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody TeamStatistic newTeamStatistic) {
    teamStatisticService.create(newTeamStatistic);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<TeamStatisticDto> update(@PathVariable Integer id,
                                                 @RequestBody TeamStatistic teamStatistic) {
    TeamStatistic teamStatisticOld;
    try {
      teamStatisticOld = teamStatisticService.getById(id);

      if (teamStatisticOld != null) {
        teamStatisticService.update(id, teamStatistic);
        TeamStatisticDto teamStatisticOldDto = new TeamStatisticDto(
            teamStatistic.getId(),
            teamStatistic.getVictories(),
            teamStatistic.getPercentageHitsOnTarget()
        );
        return new ResponseEntity<>(teamStatisticOldDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (teamStatisticService.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}