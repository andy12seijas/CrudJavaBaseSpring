package com.ejercicio.crudjava.repositorios;

import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignacionRepositorio extends CrudRepository<AsignacionEntity, Long> {
}
