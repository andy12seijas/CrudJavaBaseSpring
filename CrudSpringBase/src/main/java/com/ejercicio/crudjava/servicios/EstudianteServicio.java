package com.ejercicio.crudjava.servicios;

import com.ejercicio.crudjava.domain.entities.EstudianteEntity;

import java.util.List;

public interface EstudianteServicio {
    EstudianteEntity obtenerEstudiantePorId(Long id);

    List<EstudianteEntity> obtenerEstudiante();

    EstudianteEntity crearEstudiante(EstudianteEntity estudianteEntity);

    EstudianteEntity actualizarEstudiante(Long id, EstudianteEntity estudianteEntity);

    boolean eliminarEstudiante(Long id);



}
