package com.ejercicio.crudjava.repositorios;

import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import org.apache.el.stream.Stream;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepositorio extends CrudRepository<EstudianteEntity, Long> {

}
