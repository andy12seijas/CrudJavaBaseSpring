package com.ejercicio.crudjava.controladores;



import com.ejercicio.crudjava.domain.dto.AsignacionDto;
import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.mappers.impl.AsignacionMapper;
import com.ejercicio.crudjava.repositorios.EstudianteRepositorio;
import com.ejercicio.crudjava.servicios.AsignacionServicio;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log
public class AsignacionControlador {
    private final AsignacionServicio asignacionServicio;
    private final AsignacionMapper asignacionMapper;
    @Autowired
    private EstudianteRepositorio estudianteRepository;
    public AsignacionControlador(AsignacionServicio asignacionServicio, AsignacionMapper asignacionMapper) {
        this.asignacionServicio = asignacionServicio;
        this.asignacionMapper = asignacionMapper;
    }
    @GetMapping("/api/mostrar_A")
    public List<AsignacionDto> mostrarAsignacion() {
        List<AsignacionEntity> asignaciones = asignacionServicio.obtenerAsignacion();
        return asignaciones.stream()
                .map(asignacionMapper::mapTo)
                .collect(Collectors.toList());
    }
    @PostMapping("/crear_A")
    public AsignacionDto crearAsignacion(@RequestBody AsignacionDto asignacionDto) {
        AsignacionEntity asignacionEntity = asignacionMapper.mapFrom(asignacionDto);
        AsignacionEntity crearAsignacionEntity = asignacionServicio.crearAsignacion(asignacionEntity);
        return asignacionMapper.mapTo(crearAsignacionEntity);
    }
    @PutMapping("/api/actualizar_A/{id}")
    public AsignacionDto actualizarAsignacion(@PathVariable Long id, @RequestBody AsignacionDto asignacionDto) {
        AsignacionEntity asignacionEntity = asignacionMapper.mapFrom(asignacionDto);
        asignacionEntity.setId(id);
        AsignacionEntity actualizarAsignacionEntity = asignacionServicio.actualizarAsignacion(id, asignacionEntity);
        return asignacionMapper.mapTo(actualizarAsignacionEntity);
    }
    @DeleteMapping("/api/eliminar_A/{id}")
    public ResponseEntity<String> eliminarAsignacion(@PathVariable Long id) {
        boolean eliminado = asignacionServicio.eliminarAsignacion(id);
        if (eliminado) {
            return ResponseEntity.ok("Asignación eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignación no encontrada");
        }
    }
}

