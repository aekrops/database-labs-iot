package com.aekrops.service;

import com.aekrops.domain.Team;
import com.aekrops.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService implements  AbstractService<Team, Integer> {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  public List<Team> getAll() {
    return teamRepository.findAll();
  }

  @Override
  public Team getById(Integer id) {
    return teamRepository.getOne(id);
  }

  @Override
  public Team create(Team newTeam) {
    return teamRepository.save(newTeam);
  }

  @Override
  public Team update(Integer id, Team team) {
    if (teamRepository.findById(id).isPresent()) {
      return teamRepository.save(team);
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteById(Integer id) {
    if (teamRepository.findById(id).isPresent()) {
      teamRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
