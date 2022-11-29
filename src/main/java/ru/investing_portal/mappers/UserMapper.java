package ru.investing_portal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.investing_portal.dto.UserDto;
import ru.investing_portal.models.domain.ApplicationRole;
import ru.investing_portal.models.domain.User;

import java.util.Collections;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class, uses = {PasswordEncoderMapper.class},
        imports = {ApplicationRole.class, Collections.class})
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", expression = "java(Collections.singletonList(ApplicationRole.USER))")
    @Mapping(target = "password", qualifiedByName = "encodePassword")
    User toUser(UserDto userDto);

    UserDto toDto(User user);

}
