package com.ejercicio.crudjava.mappers.impl;

import com.ejercicio.crudjava.domain.dto.EstudianteAsignacionDto;
import com.ejercicio.crudjava.domain.entities.EstudianteAsignacionEntity;
import com.ejercicio.crudjava.domain.entities.PaisEntity;
import com.ejercicio.crudjava.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstudianteAsignacionMapper implements Mapper<EstudianteAsignacionEntity, EstudianteAsignacionDto> {
    private ModelMapper modelMapper;
    public EstudianteAsignacionMapper(ModelMapper modelMapper) {this.modelMapper=modelMapper;
    }

    @Override
    public EstudianteAsignacionDto mapTo(EstudianteAsignacionEntity estudianteAsignacionEntity) {
        return modelMapper.map(estudianteAsignacionEntity, EstudianteAsignacionDto.class);
    }

    @Override
    public EstudianteAsignacionEntity mapFrom(EstudianteAsignacionDto estudianteAsignacionDto) {
        return modelMapper.map(estudianteAsignacionDto, EstudianteAsignacionEntity.class);
    }
}
