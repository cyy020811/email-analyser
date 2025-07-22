package com.email.analyser.mapper;

import org.mapstruct.Mapper;

import com.email.analyser.dto.InteractionDto;
import com.email.analyser.model.Interaction;

@Mapper(componentModel = "spring")
public interface InteractionMapper {
    Interaction toEnitity(InteractionDto interactionDto);

    InteractionDto tDto(Interaction interaction);
}
