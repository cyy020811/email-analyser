package com.email.analyser.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.email.analyser.dto.EmailDto;
import com.email.analyser.model.Email;
import com.google.api.services.gmail.model.Message;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    EmailDto toDto(Email email);

    @Mapping(target = "user", ignore = true)
    Email toEntity(EmailDto emailDto);

    @Mapping(target = "messageId", source = "id")
    @Mapping(target = "subject", expression = "java(getHeader(message, \"Subject\"))")
    @Mapping(target = "sender", expression = "java(getHeader(message, \"From\"))")
    @Mapping(target = "receivedAt", expression = "java(getTimestamp(message))")
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "organisation", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Email toEntity(Message message);

    List<Email> toEmailList(Iterable<EmailDto> emailDtos);

    default String getHeader(Message message, String name) {
        return message.getPayload().getHeaders().stream()
                .filter(h -> h.getName().equalsIgnoreCase(name))
                .map(h -> h.getValue())
                .findFirst()
                .orElse(null);
    }

    default Timestamp getTimestamp(Message message) {
        return new Timestamp(message.getInternalDate());
    }
}
