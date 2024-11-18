package com.ejercicio.crudjava.servicios.impl;

import com.ejercicio.crudjava.domain.entities.PaisEntity;
import com.ejercicio.crudjava.repositorios.PaisRepositorio;
import com.ejercicio.crudjava.servicios.PaisServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PaisServicioImpl implements PaisServicio {

    private final PaisRepositorio paisRepositorio;

    private static final Logger log = Logger.getLogger(PaisServicioImpl.class.getName());

    @Autowired
    public PaisServicioImpl(PaisRepositorio paisRepositorio) {
        this.paisRepositorio = paisRepositorio;
    }

    @Override
    public List<PaisEntity> obtenerPaises() {
        return (List<PaisEntity>) paisRepositorio.findAll();
    }

    @Override
    public PaisEntity crearPais(PaisEntity paisEntity) {
        return paisRepositorio.save(paisEntity);
    }

    @Override
    public PaisEntity actualizarPais(Long id, PaisEntity paisEntity) {
        if (paisRepositorio.existsById(id)) {
            paisEntity.setId(id);
            return paisRepositorio.save(paisEntity);
        } else {
            log.warning("País con ID " + id + " no encontrado");
            return null;
        }
    }

    @Override
    public boolean eliminarPais(Long id) {
        if (paisRepositorio.existsById(id)) {
            paisRepositorio.deleteById(id);
            return true;
        } else {
            log.warning("País con ID " + id + " no encontrado");
            return false;
        }
    }
}
