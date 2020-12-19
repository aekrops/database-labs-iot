package com.aekrops.controller;

import com.aekrops.domain.Game;
import com.aekrops.dto.GameDto;
import com.aekrops.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/games")
@RestController
public class GameController {

  private final GameService gameService;

  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<GameDto>> getAll() {
    List<Game> games = gameService.getAll();
    List<GameDto> gameDtos = new ArrayList<>();
    for (Game game : games) {
      GameDto gameDto = new GameDto(
          game.getId(),
          game.getSeason(),
          game.getGuestsTeam(),
          game.getHostsTeam(),
          game.getTournament(),
          game.getReferee(),
          game.getStadium(),
          game.getMatchDate()
      );
      gameDtos.add(gameDto);
    }
    return new ResponseEntity<>(gameDtos, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  public ResponseEntity<GameDto> getById(@PathVariable Integer id) {
    Game game;
    try {
      game = gameService.getById(id);

      GameDto gameDto = new GameDto(
          game.getId(),
          game.getSeason(),
          game.getGuestsTeam(),
          game.getHostsTeam(),
          game.getTournament(),
          game.getReferee(),
          game.getStadium(),
          game.getMatchDate()
      );
      return new ResponseEntity<>(gameDto, HttpStatus.OK);


    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Void> create(@RequestBody Game newGame) {
    gameService.create(newGame);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}",
      consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<GameDto> update(@PathVariable Integer id, @RequestBody Game game) {
    Game gameOld;
    try {
      gameOld = gameService.getById(id);

      if (gameOld != null) {
        gameService.update(id, game);
        GameDto gameOldDto = new GameDto(
            game.getId(),
            game.getSeason(),
            game.getGuestsTeam(),
            game.getHostsTeam(),
            game.getTournament(),
            game.getReferee(),
            game.getStadium(),
            game.getMatchDate()
        );
        return new ResponseEntity<>(gameOldDto, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
    if (gameService.deleteById(id)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}