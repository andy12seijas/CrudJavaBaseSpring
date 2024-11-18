package com.ejercicio.crudjava.mappers.impl;

import com.ejercicio.crudjava.domain.dto.EstudianteDto;
import com.ejercicio.crudjava.domain.dto.PaisDto;
import com.ejercicio.crudjava.domain.entities.EstudianteEntity;
import com.ejercicio.crudjava.domain.entities.PaisEntity;
import com.ejercicio.crudjava.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaisMapper implements Mapper<PaisEntity,PaisDto> {
    private ModelMapper modelMapper;
    public PaisMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public PaisDto mapTo(PaisEntity paisEntity) {
        return  modelMapper.map(paisEntity, PaisDto.class);
    }


    @Override
    public PaisEntity mapFrom(PaisDto paisDto) {
        return modelMapper.map(paisDto, PaisEntity.class);
    }

}
