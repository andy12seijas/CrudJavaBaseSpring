package com.ejercicio.crudjava.domain.entities;

import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.ejercicio.crudjava.domain.entities.PaisEntity;
import jakarta.persistence.*;
import lombok.*;
import org.apache.el.stream.Stream;

import java.util.List;

@Entity
@Table(name = "estudiantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudianteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiantes")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 30)
    private String apellido;

    @Column(name = "edad", nullable = false)
    private int edad;

    @ManyToOne
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais")
    private PaisEntity pais;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EstudianteAsignacionEntity> estudianteAsignaciones;

    public static Stream stream() {
        return null;
    }
}
