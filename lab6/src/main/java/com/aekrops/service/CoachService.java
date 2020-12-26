package com.aekrops.service;

import com.aekrops.domain.Coach;
import com.aekrops.repository.CoachRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoachService implements AbstractService<Coach, Integer> {

  private final CoachRepository coachRepository;

  public CoachService(CoachRepository coachRepository) {
    this.coachRepository = coachRepository;
  }

  @Override
  public List<Coach> getAll() {
    return coachRepository.findAll();
  }

  @Override
  public Coach getById(Integer id) {
    return coachRepository.getOne(id);
  }

  @Override
  public Coach create(Coach newCoach) {
    return coachRepository.save(newCoach);
  }

  @Override
  public Coach update(Integer id, Coach coach) {
    if (coachRepository.findById(id).isPresent()) {
      return coachRepository.save(coach);
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteById(Integer id) {
    if (coachRepository.findById(id).isPresent()) {
      coachRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
