package com.aekrops.service;

import com.aekrops.domain.Stadium;
import com.aekrops.repository.StadiumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumService implements AbstractService<Stadium, Integer> {

  private final StadiumRepository stadiumRepository;

  public StadiumService(StadiumRepository stadiumRepository) {
    this.stadiumRepository = stadiumRepository;
  }

  @Override
  public List<Stadium> getAll() {
    return stadiumRepository.findAll();
  }

  @Override
  public Stadium getById(Integer id) {
    return stadiumRepository.getOne(id);
  }

  @Override
  public Stadium create(Stadium newStadium) {
    return stadiumRepository.save(newStadium);
  }

  @Override
  public Stadium update(Integer id, Stadium stadium) {
    if (stadiumRepository.findById(id).isPresent()) {
      return stadiumRepository.save(stadium);
    } else {
      return null;
    }
  }

  @Override
  public Boolean deleteById(Integer id) {
    if (stadiumRepository.findById(id).isPresent()) {
      stadiumRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
