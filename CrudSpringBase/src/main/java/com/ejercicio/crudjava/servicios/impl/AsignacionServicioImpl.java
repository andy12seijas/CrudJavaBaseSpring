package com.ejercicio.crudjava.servicios.impl;

import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.repositorios.AsignacionRepositorio;
import com.ejercicio.crudjava.servicios.AsignacionServicio;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class AsignacionServicioImpl implements AsignacionServicio {
    private AsignacionRepositorio asignacionRepositorio;
    public AsignacionServicioImpl(AsignacionRepositorio asignacionRepositorio) {
        this.asignacionRepositorio=asignacionRepositorio;
    }
    public List<AsignacionEntity> obtenerAsignacion() {
        return (List<AsignacionEntity>) asignacionRepositorio.findAll();
    }
    public AsignacionEntity crearAsignacion(AsignacionEntity asignacionEntity) {
        return asignacionRepositorio.save(asignacionEntity);
    }

    @Override
    public AsignacionEntity actualizarAsignacion(Long id, AsignacionEntity asignacionEntity) {
        if (asignacionRepositorio.existsById(id)) {
            asignacionEntity.setId(id);
            return asignacionRepositorio.save(asignacionEntity);
        } else {
            log.warning("Contacto con ID " + id + " no encontrado");
            return null;
        }
    }

    @Override
    public boolean eliminarAsignacion(Long id) {
        if (asignacionRepositorio.existsById(id)) {
            asignacionRepositorio.deleteById(id);
            return true;
        } else {
            log.warning("Contacto con ID " + id + " no encontrado");
            return false;
        }
    }

}
