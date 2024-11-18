package com.ejercicio.crudjava.servicios.impl;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.ejercicio.crudjava.repositorios.EstudianteAsignacionRepositorio;
import com.ejercicio.crudjava.servicios.EstudianteAsignacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class EstudianteAsignacionServicioImpl implements EstudianteAsignacionServicio {

    private final EstudianteAsignacionRepositorio estudianteAsignacionRepositorio;
    private static final Logger log = Logger.getLogger(EstudianteAsignacionServicioImpl.class.getName());

    @Autowired
    public EstudianteAsignacionServicioImpl(EstudianteAsignacionRepositorio estudianteAsignacionRepositorio) {
        this.estudianteAsignacionRepositorio = estudianteAsignacionRepositorio;
    }

    @Override
    public List<EstudianteAsignacionEntity> obtenerEstudiantesAsignaciones() {
        return (List<EstudianteAsignacionEntity>) estudianteAsignacionRepositorio.findAll();
    }

    @Override
    public EstudianteAsignacionEntity crearEstudianteAsignacion(EstudianteAsignacionEntity estudianteAsignacionEntity) {
        return estudianteAsignacionRepositorio.save(estudianteAsignacionEntity);
    }
    @Override
    public List<EstudianteAsignacionEntity> obtenerAsignacionesPorEstudiante(Long estudianteId) {
        return estudianteAsignacionRepositorio.findByEstudianteId(estudianteId);
    }

    @Override
    public EstudianteAsignacionEntity actualizarEstudianteAsignacion(Long id, EstudianteAsignacionEntity estudianteAsignacionEntity) {
        if (estudianteAsignacionRepositorio.existsById(id)) {
            estudianteAsignacionEntity.setId(id);
            return estudianteAsignacionRepositorio.save(estudianteAsignacionEntity);
        } else {
            log.warning("EstudianteAsignacion con ID " + id + " no encontrado");
            return null;
        }
    }

    @Override
    public boolean eliminarEstudianteAsignacion(Long id) {
        if (estudianteAsignacionRepositorio.existsById(id)) {
            estudianteAsignacionRepositorio.deleteById(id);
            return true;
        } else {
            log.warning("EstudianteAsignacion con ID " + id + " no encontrado");
            return false;
        }
    }
}
