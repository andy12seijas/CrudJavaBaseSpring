package com.ejercicio.crudjava.domain.dto;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionDto {
    private Long id;


    private String nombre;


    private List<EstudianteAsignacionEntity> estudianteAsignaciones;

}
