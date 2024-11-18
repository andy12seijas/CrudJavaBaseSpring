package com.ejercicio.crudjava.servicios.impl;

import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.repositorios.EstudianteRepositorio;
import com.ejercicio.crudjava.servicios.EstudianteServicio;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class EstudianteServicioImpl implements EstudianteServicio {
    private EstudianteRepositorio estudianteRepositorio;

    public EstudianteServicioImpl(EstudianteRepositorio estudianteRepositorio) {
        this.estudianteRepositorio = estudianteRepositorio;
    }
    @Override
    public EstudianteEntity obtenerEstudiantePorId(Long id) {
        return estudianteRepositorio.findById(id).orElse(null);  // Retorna el estudiante si lo encuentra, o null si no existe
    }
    @Transactional
    public EstudianteEntity actualizarEstudiante(Long id, EstudianteEntity estudianteEntity) {
        EstudianteEntity estudianteExistente = estudianteRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        estudianteExistente.setNombre(estudianteEntity.getNombre());
        estudianteExistente.setApellido(estudianteEntity.getApellido());
        estudianteExistente.setEdad(estudianteEntity.getEdad());

        if (estudianteEntity.getEstudianteAsignaciones() != null) {
            estudianteExistente.setEstudianteAsignaciones(estudianteEntity.getEstudianteAsignaciones());
        }
        return estudianteRepositorio.save(estudianteExistente);
    }

    @Override
    public List<EstudianteEntity> obtenerEstudiante() {
        return (List<EstudianteEntity>) estudianteRepositorio.findAll();
    }

    @Override
    public EstudianteEntity crearEstudiante(EstudianteEntity estudianteEntity) {
        return estudianteRepositorio.save(estudianteEntity);
    }


    @Override
    public boolean eliminarEstudiante(Long id) {
        if (estudianteRepositorio.existsById(id)) {
            estudianteRepositorio.deleteById(id);
            return true;
        } else {
            log.warning("Contacto con ID " + id + " no encontrado");
            return false;
        }
    }


}
