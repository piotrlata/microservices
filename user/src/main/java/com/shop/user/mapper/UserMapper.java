package com.shop.user.mapper;

import com.shop.common.model.dto.UserDto;
import com.shop.user.model.dao.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper extends AuditingMapper<User, UserDto> {
    User dtoToDao(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto daoToDto(User user);
}
