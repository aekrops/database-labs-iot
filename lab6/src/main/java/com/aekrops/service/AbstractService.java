package com.aekrops.service;

import java.util.List;

public interface AbstractService<E, ID> {

  public List<E> getAll();

  public E getById(ID id);

  public E create(E newObject);

  public E update(ID id, E object);

  public Boolean deleteById(ID id);

}
