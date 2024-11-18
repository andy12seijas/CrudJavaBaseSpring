package com.ejercicio.crudjava.mappers.impl;

import com.ejercicio.crudjava.domain.dto.AsignacionDto;
import com.ejercicio.crudjava.domain.dto.EstudianteDto;
import com.ejercicio.crudjava.domain.entities.AsignacionEntity;
import com.ejercicio.crudjava.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AsignacionMapper implements Mapper<AsignacionEntity, AsignacionDto> {
    private ModelMapper modelMapper;
    public AsignacionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public AsignacionDto mapTo(AsignacionEntity asignacionEntity) {
        return  modelMapper.map(asignacionEntity, AsignacionDto.class);
    }

    @Override
    public AsignacionEntity mapFrom(AsignacionDto asignacionDto) {
        return  modelMapper.map(asignacionDto, AsignacionEntity.class);
    }
}
