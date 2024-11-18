package com.ejercicio.crudjava.controladores;

import com.ejercicio.crudjava.domain.dto.EstudianteDto;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.mappers.impl.EstudianteMapper;
import com.ejercicio.crudjava.repositorios.EstudianteRepositorio;
import com.ejercicio.crudjava.servicios.EstudianteAsignacionServicio;
import com.ejercicio.crudjava.servicios.EstudianteServicio;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Log
@Controller
public class EstudianteControlador {
    private final EstudianteServicio estudianteServicio;
    private final EstudianteMapper estudianteMapper;
    private  final EstudianteAsignacionServicio estudianteAsignacionServicio;
    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    public EstudianteControlador(EstudianteServicio estudianteServicio, EstudianteMapper estudianteMapper, EstudianteAsignacionServicio estudianteAsignacionServicio) {
        this.estudianteServicio = estudianteServicio;
        this.estudianteMapper = estudianteMapper;
        this.estudianteAsignacionServicio = estudianteAsignacionServicio;
    }
    @GetMapping("/api/mostrar_E")
    public String obtenerEstudiantes( Model model) {
        List<EstudianteEntity> estudiantes = estudianteServicio.obtenerEstudiante();
        List<EstudianteDto>estudiante= estudiantes.stream()
                .map(estudianteMapper::mapTo)
                .collect(Collectors.toList());
        model.addAttribute("usuarios",estudiante);
        return "mostrar";
    }
    @GetMapping("/api/crear_E")
    public String AddFormulario(Model model){
          model.addAttribute("usuario" ,new EstudianteDto());
          return "add";
    }
    @PostMapping("/api/crear_E")
    public String crearEstudiante(@RequestBody @ModelAttribute EstudianteDto estudianteDto) {
        EstudianteEntity estudianteEntity = estudianteMapper.mapFrom(estudianteDto);
        EstudianteEntity estudianteCreado = estudianteServicio.crearEstudiante(estudianteEntity);
        estudianteMapper.mapTo(estudianteCreado);
        return "redirect:/api/mostrar_E";
    }
    @GetMapping("/actualizarE/{id}")
    public String formActualizar(@PathVariable Long id, Model model) {

        EstudianteEntity estudiante = estudianteServicio.obtenerEstudiantePorId(id);
        if (estudiante != null) {
            model.addAttribute("usuario", estudiante);
            return "actualizarE";
        } else {

            return "redirect:/error";
        }
    }
    @PostMapping("/actualizarE/{id}")
    public String actualizarEstudiante(@PathVariable Long id, @ModelAttribute EstudianteDto estudianteDto) {

        EstudianteEntity estudianteEntity = estudianteMapper.mapFrom(estudianteDto);
        estudianteEntity.setId(id);

        if (estudianteDto.getEstudianteAsignaciones() != null) {
            estudianteEntity.setEstudianteAsignaciones(estudianteDto.getEstudianteAsignaciones());
        }
        EstudianteEntity estudianteActualizado = estudianteServicio.actualizarEstudiante(id, estudianteEntity);
        if (estudianteActualizado != null) {
            return "redirect:/api/mostrar_E";
        } else {
            return "redirect:/error";
        }
    }




    @PostMapping ("/api/eliminarE/{id}")
    public Object eliminarEstudiante(@PathVariable Long id) {
        boolean eliminarasi=estudianteAsignacionServicio.eliminarEstudianteAsignacion(id);
        boolean eliminado = estudianteServicio.eliminarEstudiante(id);

        if (eliminarasi && eliminado) {
            ResponseEntity.ok("Estudiante eliminado correctamente");
        }
        return "redirect:/api/mostrar_E";

    }


}
