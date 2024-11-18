package com.ejercicio.crudjava.domain.entities;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "asignaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AsignacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion")
    private Long id;

    @Column(name = "nombre_asi", nullable = false, length = 50)
    private String nombre;

    @OneToMany(mappedBy = "asignacion")
    @JsonBackReference
    private List<EstudianteAsignacionEntity> estudianteAsignaciones;
}
