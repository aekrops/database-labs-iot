package com.aekrops.service;

import com.aekrops.domain.TeamStatistic;
import com.aekrops.repository.TeamStatisticRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamStatisticService implements AbstractService<TeamStatistic, Integer> {

  private final TeamStatisticRepository teamStatisticRepository;

  public TeamStatisticService(TeamStatisticRepository teamStatisticRepository) {
    this.teamStatisticRepository = teamStatisticRepository;
  }

  @Override
  public List<TeamStatistic> getAll() {
    return teamStatisticRepository.findAll();
  }

  @Override
  public TeamStatistic getById(Integer id) {
    return teamStatisticRepository.getOne(id);
  }

  @Override
  public TeamStatistic create(TeamStatistic newTeamStatistic) {
    return teamStatisticRepository.save(newTeamStatistic);
  }

  @Override
  public TeamStatistic update(Integer id, TeamStatistic teamStatistic) {
    if (teamStatisticRepository.findById(id).isPresent()) {
      return teamStatisticRepository.save(teamStatistic);
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteById(Integer id) {
    if (teamStatisticRepository.findById(id).isPresent()) {
      teamStatisticRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
