package com.aekrops.controller;

import com.aekrops.domain.Referee;
import com.aekrops.dto.RefereeDto;
import com.aekrops.service.RefereeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/referees")
@RestController
public class RefereeController {

  private final RefereeService refereeService;

  public RefereeController(RefereeService refereeService) {
    this.refereeService = refereeService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<RefereeDto>> getAll() {
    List<Referee> referees = refereeService.getAll();
    List<RefereeDto> refereeDtos = new ArrayList<>();
    for (Referee referee : referees) {
      RefereeDto refereeDto = new RefereeDto(
          referee.getId(),
          referee.getName(),
          referee.getAge()
      );
      refereeDtos.add(refereeDto);
    }
    return new ResponseEntity<>(refereeDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<RefereeDto> getById(@PathVariable Integer id) {
    Referee referee;
    try {
      referee = refereeService.getById(id);

      RefereeDto refereeDto = new RefereeDto(
          referee.getId(),
          referee.getName(),
          referee.getAge()
      );
      return new ResponseEntity<>(refereeDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Referee newReferee) {
    refereeService.create(newReferee);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<RefereeDto> update(@PathVariable Integer id, @RequestBody Referee referee) {
    Referee refereeOld;
    try {
      refereeOld = refereeService.getById(id);
      if (refereeOld != null) {
        refereeService.update(id, referee);
        RefereeDto refereeOldDto = new RefereeDto(
            referee.getId(),
            referee.getName(),
            referee.getAge()
        );
        return new ResponseEntity<>(refereeOldDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (refereeService.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}

