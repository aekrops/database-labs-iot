package com.aekrops.controller;

import com.aekrops.domain.Stadium;
import com.aekrops.dto.StadiumDto;
import com.aekrops.service.StadiumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/stadiums")
@RestController
public class StadiumController {

  private final StadiumService stadiumService;

  public StadiumController(StadiumService stadiumService) {
    this.stadiumService = stadiumService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<StadiumDto>> getAll() {
    List<Stadium> stadiums = stadiumService.getAll();
    List<StadiumDto> stadiumDtos = new ArrayList<>();
    for (Stadium stadium : stadiums) {
      StadiumDto stadiumDto = new StadiumDto(
          stadium.getId(),
          stadium.getName(),
          stadium.getCity(),
          stadium.getCountry()
      );
      stadiumDtos.add(stadiumDto);
    }
    return new ResponseEntity<>(stadiumDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<StadiumDto> getById(@PathVariable Integer id) {
    Stadium stadium;
    try {
      stadium = stadiumService.getById(id);

      StadiumDto stadiumDto = new StadiumDto(
          stadium.getId(),
          stadium.getName(),
          stadium.getCity(),
          stadium.getCountry()
      );
      return new ResponseEntity<>(stadiumDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Stadium newStadium) {
    stadiumService.create(newStadium);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<StadiumDto> update(@PathVariable Integer id, @RequestBody Stadium stadium) {
    Stadium stadiumOld;
    try {
      stadiumOld = stadiumService.getById(id);
      if (stadiumOld != null) {
        stadiumService.update(id, stadium);
        StadiumDto stadiumOldDto = new StadiumDto(
            stadium.getId(),
            stadium.getName(),
            stadium.getCity(),
            stadium.getCountry()
        );
        return new ResponseEntity<>(stadiumOldDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (stadiumService.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
