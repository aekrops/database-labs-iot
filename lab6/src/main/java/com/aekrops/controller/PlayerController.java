package com.aekrops.controller;

import com.aekrops.domain.Player;
import com.aekrops.dto.PlayerDto;
import com.aekrops.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/players")
@RestController
public class PlayerController {

  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<PlayerDto>> getAll() {
    List<Player> players = playerService.getAll();
    List<PlayerDto> playerDtos = new ArrayList<>();
    for (Player player : players) {
      PlayerDto playerDto = new PlayerDto(
          player.getId(),
          player.getTeam(),
          player.getName(),
          player.getAge()
      );
      playerDtos.add(playerDto);
    }
    return new ResponseEntity<>(playerDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<PlayerDto> getById(@PathVariable Integer id) {
    Player player;
    try {
      player = playerService.getById(id);

      PlayerDto playerDto = new PlayerDto(
          player.getId(),
          player.getTeam(),
          player.getName(),
          player.getAge()
      );
      return new ResponseEntity<>(playerDto, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Player newPlayer) {
    playerService.create(newPlayer);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<PlayerDto> update(@PathVariable Integer id, @RequestBody Player player) {
    Player playerOld;
    try {
      playerOld = playerService.getById(id);
      if (playerOld != null) {
        playerService.update(id, player);
        PlayerDto playerOldDto = new PlayerDto(
            player.getId(),
            player.getTeam(),
            player.getName(),
            player.getAge()
        );
        return new ResponseEntity<>(playerOldDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (playerService.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else{
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
