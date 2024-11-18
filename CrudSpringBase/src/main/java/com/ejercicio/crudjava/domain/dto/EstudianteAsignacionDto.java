package com.ejercicio.crudjava.domain.dto;

import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudianteAsignacionDto {

    private Long id;
    private Long estudianteId;  // Usar solo el ID, no el objeto completo
    private Long asignacionId;  // Usar solo el ID
    private Float nota;

    // Si necesitas más información sobre Estudiante o Asignación, puedes usar DTOs también.
}
