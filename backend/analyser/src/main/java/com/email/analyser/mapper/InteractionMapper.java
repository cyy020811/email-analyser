package com.email.analyser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.email.analyser.dto.InteractionDto;
import com.email.analyser.model.Interaction;

@Mapper(componentModel = "spring")
public interface InteractionMapper {

    @Mapping(target = "user", ignore = true)
    Interaction toEnitity(InteractionDto interactionDto);

    InteractionDto toDto(Interaction interaction);

    List<InteractionDto> toInteractionDtoList(Iterable<Interaction> interactions);
}
