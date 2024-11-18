package com.ejercicio.crudjava.controladores;

import com.ejercicio.crudjava.domain.dto.PaisDto;
import com.ejercicio.crudjava.domain.entities.PaisEntity;
import com.ejercicio.crudjava.mappers.impl.PaisMapper;
import com.ejercicio.crudjava.repositorios.PaisRepositorio;
import com.ejercicio.crudjava.servicios.PaisServicio;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log

public class PaisControlador {
    private final PaisServicio paisServicio;
    private final PaisMapper paisMapper;
    @Autowired
    private PaisRepositorio paisRepositorio;
    public PaisControlador(PaisServicio paisServicio, PaisMapper paisMapper) {
        this.paisServicio = paisServicio;
        this.paisMapper = paisMapper;
    }
    @GetMapping("/api/mostrar_P")
    public List<PaisDto> mostrarPaises() {
        List<PaisEntity> paises = paisServicio.obtenerPaises();
        return paises.stream()
                .map(paisMapper::mapTo)
                .collect(Collectors.toList());
    }
    @PostMapping("/api/crear_P")
    public PaisDto crearPais(@RequestBody PaisDto paisDto) {
        PaisEntity paisEntity = paisMapper.mapFrom(paisDto);
        PaisEntity creado = paisServicio.crearPais(paisEntity);
        return paisMapper.mapTo(creado);
    }
    @PutMapping("/actualizarP/{id}")
    public PaisDto actualizarPais(@PathVariable Long id, @RequestBody PaisDto paisDto) {
        PaisEntity paisEntity = paisMapper.mapFrom(paisDto);
        paisEntity.setId(id);
        PaisEntity actualizado = paisServicio.actualizarPais(id, paisEntity);
        return paisMapper.mapTo(actualizado);
    }
    @DeleteMapping("/eliminarP/{id}")
    public ResponseEntity<String> eliminarPais(@PathVariable Long id) {
        boolean eliminado = paisServicio.eliminarPais(id);
        if (eliminado) {
            return ResponseEntity.ok("País eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("País no encontrado");
        }
    }
}
