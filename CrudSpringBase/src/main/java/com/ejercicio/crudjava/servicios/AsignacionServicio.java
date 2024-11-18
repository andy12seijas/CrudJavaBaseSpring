package com.ejercicio.crudjava.servicios;

import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;

import java.util.List;

public interface AsignacionServicio {
    List<AsignacionEntity> obtenerAsignacion();

    AsignacionEntity crearAsignacion(AsignacionEntity asignacionEntity);
    AsignacionEntity actualizarAsignacion(Long id, AsignacionEntity asignacionEntity);

    boolean eliminarAsignacion(Long id);
}
