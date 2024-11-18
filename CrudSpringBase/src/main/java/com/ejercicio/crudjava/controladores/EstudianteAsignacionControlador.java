package com.ejercicio.crudjava.controladores;

import com.ejercicio.crudjava.domain.dto.EstudianteAsignacionDto;
import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.ejercicio.crudjava.mappers.impl.EstudianteAsignacionMapper;
import com.ejercicio.crudjava.repositorios.AsignacionRepositorio;
import com.ejercicio.crudjava.servicios.EstudianteAsignacionServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;


import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.repositorios.EstudianteRepositorio;
import com.ejercicio.crudjava.servicios.EstudianteServicio;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Controller
@Log
public class EstudianteAsignacionControlador {
    private final EstudianteAsignacionServicio estudianteAsignacionServicio;
    private final EstudianteAsignacionMapper estudianteAsignacionMapper;
    private final EstudianteServicio estudianteServicio;
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Autowired
    private final AsignacionRepositorio asignacionRepositorio;
    public EstudianteAsignacionControlador(EstudianteAsignacionServicio estudianteAsignacionServicio, EstudianteAsignacionMapper estudianteAsignacionMapper, EstudianteServicio estudianteServicio, AsignacionRepositorio asignacionRepositorio) {
        this.estudianteAsignacionServicio = estudianteAsignacionServicio;
        this.estudianteAsignacionMapper = estudianteAsignacionMapper;
        this.estudianteServicio = estudianteServicio;
        this.asignacionRepositorio = asignacionRepositorio;
    }
    @GetMapping("/api/mostrar_EA")
    public List<EstudianteAsignacionDto> mostrarEstudianteAsignacion() {
        List<EstudianteAsignacionEntity> estudianteAsignaciones = estudianteAsignacionServicio.obtenerEstudiantesAsignaciones();
        return estudianteAsignaciones.stream()
                .map(estudianteAsignacionMapper::mapTo)
                .collect(Collectors.toList());
    }
    @GetMapping("/verMaterias/{id}")
    public String verMaterias(@PathVariable @ModelAttribute  Long id, Model model) {
        EstudianteEntity estudiante = estudianteServicio.obtenerEstudiantePorId(id);
        List<AsignacionEntity> asignacionesDisponibles = (List<AsignacionEntity>) asignacionRepositorio.findAll();
        List<EstudianteAsignacionEntity> asignacionesEstudiante = estudianteAsignacionServicio.obtenerAsignacionesPorEstudiante(id);

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("asignacionesDisponibles", asignacionesDisponibles);
        model.addAttribute("asignacionesEstudiante", asignacionesEstudiante);
        return "verMaterias";
    }
    @PostMapping("/api/crear_EA")
    public String crearEstudianteAsignacion(@ModelAttribute EstudianteAsignacionDto estudianteAsignacionDto, Model model) {
        EstudianteEntity estudiante = estudianteRepositorio.findById(estudianteAsignacionDto.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));
        AsignacionEntity asignacion = asignacionRepositorio.findById(estudianteAsignacionDto.getAsignacionId())
                .orElseThrow(() -> new RuntimeException("Asignación no encontrada"));
        EstudianteAsignacionEntity estudianteAsignacionEntity = estudianteAsignacionMapper.mapFrom(estudianteAsignacionDto);
        estudianteAsignacionEntity.setEstudiante(estudiante);
        estudianteAsignacionEntity.setAsignacion(asignacion);
        estudianteAsignacionEntity.setNota(estudianteAsignacionDto.getNota());

        estudianteAsignacionServicio.crearEstudianteAsignacion(estudianteAsignacionEntity);


        return "redirect:/verMaterias/" + estudianteAsignacionDto.getEstudianteId();
    }

    @PutMapping("/api/actualizar_EA/{id}")
    public EstudianteAsignacionDto actualizarEstudianteAsignacion(@PathVariable Long id, @ModelAttribute EstudianteAsignacionDto estudianteAsignacionDto) {
        EstudianteAsignacionEntity estudianteAsignacionEntity = estudianteAsignacionMapper.mapFrom(estudianteAsignacionDto);
        estudianteAsignacionEntity.setId(id);
        EstudianteAsignacionEntity actualizado = estudianteAsignacionServicio.actualizarEstudianteAsignacion(id, estudianteAsignacionEntity);
        return estudianteAsignacionMapper.mapTo(actualizado);
    }
    @PostMapping ("/api/eliminar_EA/{id}")
    public Object eliminarEstudianteAsignacion(@PathVariable Long id, HttpServletRequest request  ) {
        boolean eliminado = estudianteAsignacionServicio.eliminarEstudianteAsignacion(id);
        if (eliminado) {
            String referer = request.getHeader("Referer");
            return "redirect:" + referer;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Relación Estudiante-Asignación no encontrada");
        }
    }
}
