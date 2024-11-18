package com.ejercicio.crudjava.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteAsignacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    @JsonBackReference
    private EstudianteEntity estudiante;

    @ManyToOne
    @JoinColumn(name = "asignacion_id")
    private AsignacionEntity asignacion;
    private Float nota;
}
