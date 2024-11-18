package com.ejercicio.crudjava.servicios;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;

import java.util.List;

public interface EstudianteAsignacionServicio {
    List<EstudianteAsignacionEntity> obtenerEstudiantesAsignaciones();

    EstudianteAsignacionEntity crearEstudianteAsignacion(EstudianteAsignacionEntity estudianteAsignacionEntity);

    List<EstudianteAsignacionEntity> obtenerAsignacionesPorEstudiante(Long estudianteId);

    EstudianteAsignacionEntity actualizarEstudianteAsignacion(Long id, EstudianteAsignacionEntity estudianteAsignacionEntity);

    boolean eliminarEstudianteAsignacion(Long id);
}
