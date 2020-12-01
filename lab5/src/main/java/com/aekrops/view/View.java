package com.aekrops.view;


import com.aekrops.controller.CoachController;
import com.aekrops.controller.GameController;
import com.aekrops.controller.PlayerController;
import com.aekrops.controller.RefereeController;
import com.aekrops.controller.StadiumController;
import com.aekrops.controller.TeamController;
import com.aekrops.controller.TeamStatisticController;
import com.aekrops.model.entity.Coach;
import com.aekrops.model.entity.Game;
import com.aekrops.model.entity.Player;
import com.aekrops.model.entity.Referee;
import com.aekrops.model.entity.Stadium;
import com.aekrops.model.entity.Team;
import com.aekrops.model.entity.TeamStatistic;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class View {

  private final CoachController coachController = new CoachController();
  private final GameController gameController = new GameController();
  private final PlayerController playerController = new PlayerController();
  private final RefereeController refereeController = new RefereeController();
  private final StadiumController stadiumController = new StadiumController();
  private final TeamController teamController = new TeamController();
  private final TeamStatisticController teamStatisticController = new TeamStatisticController();

  private final Map<String, Printable> menu;
  private static final Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);


  public final void show() throws SQLException {
    String keyMenu = "";
    while (!keyMenu.equals("Q")) {
      displayMenu();
      System.out.println("Please, select menu point.");
      keyMenu = input.nextLine().toUpperCase();
      try {
        menu.get(keyMenu).print();
      } catch (Exception ignored) {

      }
    }
  }

  private void displayMenu() {
    System.out.println(" ---------------------------------------- ");
    System.out.println("|                     |                    |");
    System.out.println("|       TABLES        |       METHODS      |");
    System.out.println("|                     |                    |");
    System.out.println(" ---------------------------------------- ");
    System.out.println("|  1 - Match          |  1 - get all       |");
    System.out.println("|  2 - Team           |  2 - get by id     |");
    System.out.println("|  3 - Player         |  3 - create        |");
    System.out.println("|  4 - Coach          |  4 - update        |");
    System.out.println("|  5 - Referee        |  5 - delete        |");
    System.out.println("|  6 - Stadium        |                    |");
    System.out.println("|  7 - Team statistic |                    |");
    System.out.println(" ------------------------------------------ ");
    System.out.println("|                  Q - exit                |");
    System.out.println(" ------------------------------------------ ");
  }

  public View() {
    menu = new LinkedHashMap<>();


    // match
    menu.put("11", this::findAllMatches);
    menu.put("12", this::findMatch);
    menu.put("13", this::createMatch);
    menu.put("14", this::updateMatch);
    menu.put("15", this::deleteMatch);
    // team
    menu.put("21", this::findAllTeams);
    menu.put("22", this::findTeam);
    menu.put("23", this::createTeam);
    menu.put("24", this::updateTeam);
    menu.put("25", this::deleteTeam);
    // player
    menu.put("31", this::findAllPlayers);
    menu.put("32", this::findPlayer);
    menu.put("33", this::createPlayer);
    menu.put("34", this::updatePlayer);
    menu.put("35", this::deletePlayer);
    // coach
    menu.put("41", this::findAllCoaches);
    menu.put("42", this::findCoach);
    menu.put("43", this::createCoach);
    menu.put("44", this::updateCoach);
    menu.put("45", this::deleteCoach);
    // referee
    menu.put("51", this::findAllReferies);
    menu.put("52", this::findReferee);
    menu.put("53", this::createReferee);
    menu.put("54", this::updateReferee);
    menu.put("55", this::deleteReferee);
    // stadium
    menu.put("61", this::findAllStadiums);
    menu.put("62", this::findStadium);
    menu.put("63", this::createStadium);
    menu.put("64", this::updateStadium);
    menu.put("65", this::deleteStadium);
    // teamStatistic
    menu.put("71", this::findAllTeamStatistics);
    menu.put("72", this::findTeamStatistic);
    menu.put("73", this::createTeamStatistic);
    menu.put("74", this::updateTeamStatistic);
    menu.put("75", this::deleteTeamStatistic);
  }

  /*  ----- Coach -----  */

  private Coach getCoachDataByInputs() {
    System.out.println("Enter coach's id: ");
    Integer id = input.nextInt();
    System.out.println("Enter name: ");
    String name = input.next();
    System.out.println("Enter age: ");
    Integer age = input.nextInt();
    return new Coach(id, name, age);
  }

  private void findAllCoaches() throws SQLException {
    System.out.println("\nCoaches:");
    System.out.println(coachController.findAll());
  }

  private void findCoach() throws SQLException {
    System.out.println("\nEnter the ID for a coach to find");
    Integer id = input.nextInt();
    System.out.println(coachController.find(id));
  }

  private void createCoach() throws SQLException {
    System.out.println("[Creating a coach] \n");
    Coach coachData = getCoachDataByInputs();
    coachController.create(coachData);
    System.out.println("A new coach was been inserted into the database!");
  }

  private void updateCoach() throws SQLException {
    System.out.println("[Updating a coach] \n");
    Coach newCoachData = getCoachDataByInputs();
    coachController.update(newCoachData.getId(), newCoachData);
    System.out.println("The coach with id " + newCoachData.getId() + " was been updated!");
  }

  private void deleteCoach() throws SQLException {
    System.out.println("[Deleting the coach] \n");
    Integer id = input.nextInt();
    coachController.delete(id);
    System.out.println("Coach with id " + id + " was been deleted!");
  }

  /* ----- Game ----- */

  private Game getMatchDataByInputs() {
    System.out.println("Enter match's id: ");
    Integer id = input.nextInt();
    System.out.println("Enter name of season: ");
    String season = input.next();
    System.out.println("Enter name of guest team: ");
    String guestTeam = input.next();
    System.out.println("Enter name of host team: ");
    String hostTeam = input.next();
    System.out.println("Enter name of tournament: ");
    String tournament = input.next();
    System.out.println("Enter referee's name: ");
    String referee = input.next();
    System.out.println("Enter stadium's name: ");
    String stadium = input.next();
    System.out.println("Enter date of match in format (2020-03-12)");
    String date = input.next();
    return new Game(id, season, guestTeam, hostTeam, tournament, referee, stadium, date);
  }

  private void findAllMatches() throws SQLException {
    System.out.println("\nMatches:");
    System.out.println(gameController.findAll());
  }

  private void findMatch() throws SQLException {
    System.out.println("Enter match's id: ");
    Integer id = input.nextInt();
    System.out.println(gameController.find(id));
  }

  private void createMatch() throws SQLException {
    System.out.println("[Creating a match]");
    Game gameData = getMatchDataByInputs();
    gameController.create(gameData);
    System.out.println("A new match was been inserted into the database!");
  }

  private void updateMatch() throws SQLException {
    System.out.println("[Updating a match]");
    Game newGameData = getMatchDataByInputs();
    gameController.update(newGameData.getId(), newGameData);
    System.out.println("The match with id " + newGameData.getId() + " was been updated!");
  }

  private void deleteMatch() throws SQLException {
    System.out.println("[Deleting the match]");
    Integer id = input.nextInt();
    gameController.delete(id);
    System.out.println("Match with id " + id + " was been deleted!");
  }

  /*  ----- Player -----  */

  private Player getPlayerDataByInputs() {
    System.out.println("Enter player's id: ");
    Integer id = input.nextInt();
    System.out.println("Enter team's id: ");
    Integer teamId = input.nextInt();
    System.out.println("Enter player's name: ");
    String name = input.next();
    System.out.println("Enter player's age: ");
    Integer age = input.nextInt();
    return new Player(id, teamId, name, age);
  }

  private void findAllPlayers() {
    System.out.println("\nPlayers:");
    System.out.println(playerController.findAll());
  }

  private void findPlayer() {
    System.out.println("Enter player's id: ");
    Integer id = input.nextInt();
    System.out.println(playerController.find(id));
  }

  private void createPlayer() throws SQLException {
    System.out.println("[Creating a player]");
    Player playerData = getPlayerDataByInputs();
    playerController.create(playerData);
    System.out.println("A new player was been inserted into the database!");
  }

  private void updatePlayer() {
    System.out.println("[Updating a player]");
    Player newPlayerData = getPlayerDataByInputs();
    playerController.update(newPlayerData.getId(), newPlayerData);
    System.out.println("The player with id " + newPlayerData.getId() + " was been updated!");
  }

  private void deletePlayer() {
    System.out.println("[Deleting the player]");
    Integer id = input.nextInt();
    playerController.delete(id);
    System.out.println("Player with id " + id + " was been deleted!");
  }

  /*  ----- Referee -----  */
  private Referee getRefereeDataByInputs() {
    System.out.println("Enter referee's id: ");
    Integer id = input.nextInt();
    System.out.println("Enter referee's name: ");
    String name = input.next();
    System.out.println("Enter player's age: ");
    Integer age = input.nextInt();
    return new Referee(id, name, age);
  }

  private void findAllReferies() {
    System.out.println("\nReferies:");
    System.out.println(refereeController.findAll());
  }

  private void findReferee() {
    System.out.println("Enter referee's id: ");
    Integer id = input.nextInt();
    System.out.println(refereeController.find(id));
  }

  private void createReferee() throws SQLException {
    System.out.println("[Creating a referee]");
    Referee refereeData = getRefereeDataByInputs();
    refereeController.create(refereeData);
    System.out.println("A new referee was been inserted into the database!");
  }

  private void updateReferee() {
    System.out.println("[Updating a referee]");
    Referee newRefereeData = getRefereeDataByInputs();
    refereeController.update(newRefereeData.getId(), newRefereeData);
    System.out.println("The referee with id " + newRefereeData.getId() + " was been updated!");
  }

  private void deleteReferee() {
    System.out.println("[Deleting the referee]");
    Integer id = input.nextInt();
    refereeController.delete(id);
    System.out.println("Referee with id " + id + " was been deleted!");
  }


  /*  ----- Stadium -----  */

  private Stadium getStadiumDataByInputs() {
    System.out.println("Enter stadium's id: ");
    Integer id = input.nextInt();
    System.out.println("Enter stadium's name: ");
    String name = input.next();
    System.out.println("Enter stadium's location (city): ");
    String city = input.next();
    System.out.println("Enter stadium's location (coutry): ");
    String country = input.next();
    return new Stadium(id, name, city, country);
  }

  private void findAllStadiums() {
    System.out.println("\nStadiums:");
    System.out.println(stadiumController.findAll());
  }

  private void findStadium() {
    System.out.println("Enter stadium's id: ");
    Integer id = input.nextInt();
    System.out.println(stadiumController.find(id));
  }

  private void createStadium() throws SQLException {
    System.out.println("[Creating a stadium]");
    Stadium stadiumData = getStadiumDataByInputs();
    stadiumController.create(stadiumData);
    System.out.println("A new stadium was been inserted into the database!");
  }

  private void updateStadium() {
    System.out.println("[Updating a stadium]");
    Stadium newStadiumData = getStadiumDataByInputs();
    stadiumController.update(newStadiumData.getId(), newStadiumData);
    System.out.println("The referee with id " + newStadiumData.getId() + " was been updated!");
  }

  private void deleteStadium() {
    System.out.println("[Deleting the stadium]");
    Integer id = input.nextInt();
    stadiumController.delete(id);
    System.out.println("Referee with id " + id + " was been deleted!");
  }



  /*  ----- Team -----  */

  private Team getTeamDataByInputs() {
    System.out.println("Enter team's id: ");
    Integer id = input.nextInt();
    System.out.println("Enter team's name: ");
    String name = input.next();
    System.out.println("Enter team's statistic id: ");
    Integer statistic_id = input.nextInt();
    System.out.println("Enter team's coach id: ");
    Integer coach_id = input.nextInt();
    return new Team(id, name, statistic_id, coach_id);
  }

  private void findAllTeams() {
    System.out.println("\nTeams:");
    System.out.println(teamController.findAll());
  }

  private void findTeam() {
    System.out.println("Enter team's id: ");
    Integer id = input.nextInt();
    System.out.println(teamController.find(id));
  }

  private void createTeam() throws SQLException {
    System.out.println("[Creating a team]");
    Team teamData = getTeamDataByInputs();
    teamController.create(teamData);
    System.out.println("A new team was been inserted into the database!");
  }

  private void updateTeam() {
    System.out.println("[Updating a team]");
    Team newTeamData = getTeamDataByInputs();
    teamController.update(newTeamData.getId(), newTeamData);
    System.out.println("The team with id " + newTeamData.getId() + " was been updated!");
  }

  private void deleteTeam() {
    System.out.println("[Deleting the team]");
    Integer id = input.nextInt();
    teamController.delete(id);
    System.out.println("Team with id " + id + " was been deleted!");
  }

  /*  ----- TeamStatistic -----  */

  private TeamStatistic getTeamStatisticDataByInputs() {
    System.out.println("Enter team's statistic id: ");
    Integer id = input.nextInt();
    System.out.println("Enter team's statictic victories: ");
    Integer victories = input.nextInt();
    System.out.println("Enter team's statistic percentage hits on target: ");
    Integer percentage_hits_on_target = input.nextInt();
    return new TeamStatistic(id, victories, percentage_hits_on_target);
  }

  private void findAllTeamStatistics() {
    System.out.println("\nTeam's statictic:");
    System.out.println(teamStatisticController.findAll());
  }

  private void findTeamStatistic() {
    System.out.println("Enter team's statistic id: ");
    Integer id = input.nextInt();
    System.out.println(teamStatisticController.find(id));
  }

  private void createTeamStatistic() throws SQLException {
    System.out.println("[Creating a team's statistic]");
    TeamStatistic teamStatisticData = getTeamStatisticDataByInputs();
    teamStatisticController.create(teamStatisticData);
    System.out.println("A new team was been inserted into the database!");
  }

  private void updateTeamStatistic() {
    System.out.println("[Updating a team statistic]");
    TeamStatistic newTeamStatisticData = getTeamStatisticDataByInputs();
    teamStatisticController.update(newTeamStatisticData.getId(), newTeamStatisticData);
    System.out.println("The team with id " + newTeamStatisticData.getId() + " was been updated!");
  }

  private void deleteTeamStatistic() {
    System.out.println("[Deleting the team statistic]");
    Integer id = input.nextInt();
    teamStatisticController.delete(id);
    System.out.println("Team statistic with id " + id + " was been deleted!");
  }
}
