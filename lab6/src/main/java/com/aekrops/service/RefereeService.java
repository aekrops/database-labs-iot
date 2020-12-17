package com.aekrops.service;

import com.aekrops.domain.Referee;
import com.aekrops.repository.RefereeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefereeService implements AbstractService<Referee, Integer> {

  private final RefereeRepository refereeRepository;

  public RefereeService(RefereeRepository refereeRepository) {
    this.refereeRepository = refereeRepository;
  }

  @Override
  public List<Referee> getAll() {
    return refereeRepository.findAll();
  }

  @Override
  public Referee getById(Integer id) {
    return refereeRepository.getOne(id);
  }

  @Override
  public Referee create(Referee newReferee) {
    return refereeRepository.save(newReferee);
  }

  @Override
  public Referee update(Integer id, Referee referee) {
    if (refereeRepository.findById(id).isPresent()) {
      return refereeRepository.save(referee);
    } else {
      return null;
    }
  }

  @Override
  public void deleteById(Integer id) {
    if (refereeRepository.findById(id).isPresent()) {
      refereeRepository.deleteById(id);
    }
  }
}
