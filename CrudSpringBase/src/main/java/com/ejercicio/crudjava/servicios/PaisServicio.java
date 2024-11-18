package com.ejercicio.crudjava.servicios;

import com.ejercicio.crudjava.domain.entities.PaisEntity;

import java.util.List;

public interface PaisServicio {
    List<PaisEntity> obtenerPaises();

    PaisEntity crearPais(PaisEntity paisEntity);

    PaisEntity actualizarPais(Long id, PaisEntity paisEntity);

    boolean eliminarPais(Long id);
}
