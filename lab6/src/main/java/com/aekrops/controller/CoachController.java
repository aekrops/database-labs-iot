package com.aekrops.controller;

import com.aekrops.domain.Coach;
import com.aekrops.dto.CoachDto;
import com.aekrops.service.CoachService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/coaches")
@RestController
public class CoachController {

  private final CoachService coachService;

  public CoachController(CoachService coachService) {
    this.coachService = coachService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<CoachDto>> getAll() {
    List<Coach> coaches = coachService.getAll();
    List<CoachDto> coachDtos = new ArrayList<>();
    for (Coach coach : coaches) {
      CoachDto coachDto = new CoachDto(
          coach.getId(),
          coach.getName(),
          coach.getAge()
      );
      coachDtos.add(coachDto);
    }
    return new ResponseEntity<>(coachDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<CoachDto> getById(@PathVariable Integer id) {
    Coach coach = coachService.getById(id);
    if (coach != null) {
      CoachDto coachDto = new CoachDto(
          coach.getId(),
          coach.getName(),
          coach.getAge()
      );
      return new ResponseEntity<>(coachDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Coach newCoach) {
    coachService.create(newCoach);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<CoachDto> update(@PathVariable Integer id, @RequestBody Coach coach) {
    Coach coachOld = coachService.getById(id);
    if (coachOld != null) {
      coachService.update(id, coach);
      CoachDto coachOldDto = new CoachDto(
          coachOld.getId(),
          coachOld.getName(),
          coachOld.getAge()
      );
      return new ResponseEntity<>(coachOldDto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (coachService.getById(id) != null) {
      coachService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
