package com.ejercicio.crudjava.mappers.impl;

import com.ejercicio.crudjava.domain.dto.EstudianteDto;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EstudianteMapper implements Mapper<EstudianteEntity, EstudianteDto> {
    private ModelMapper modelMapper;
    public EstudianteMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public EstudianteDto mapTo(EstudianteEntity estudianteEntity) {
        return  modelMapper.map(estudianteEntity, EstudianteDto.class);
    }

    @Override
    public EstudianteEntity mapFrom(EstudianteDto estudianteDto) {
        return modelMapper.map(estudianteDto, EstudianteEntity.class);
    }
}
