package com.ejercicio.crudjava.repositorios;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudianteAsignacionRepositorio extends CrudRepository<EstudianteAsignacionEntity, Long> {
    List<EstudianteAsignacionEntity> findByEstudianteId(Long estudianteId);
}
