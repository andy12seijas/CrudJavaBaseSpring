package com.ejercicio.crudjava.repositorios;

import com.ejercicio.crudjava.domain.entities.PaisEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaisRepositorio extends CrudRepository<PaisEntity,Long> {
}
