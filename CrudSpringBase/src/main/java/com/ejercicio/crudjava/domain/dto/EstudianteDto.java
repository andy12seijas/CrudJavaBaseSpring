package com.ejercicio.crudjava.domain.dto;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.ejercicio.crudjava.domain.entities.PaisEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudianteDto {
    private Long id;


    private String nombre;


    private String apellido;


    private int edad;


    private PaisDto pais;


    private List<EstudianteAsignacionEntity> estudianteAsignaciones;

    public EstudianteDto(Long id, String nombre, String apellido, int edad, List<EstudianteAsignacionEntity> estudianteAsignaciones) {
        this.id=id;
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.estudianteAsignaciones=estudianteAsignaciones;

    }
}
