package com.email.analyser.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.email.analyser.dto.UserDto;
import com.email.analyser.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "interactions", ignore = true)
    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);
}
