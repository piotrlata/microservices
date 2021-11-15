package com.shop.user.mapper;

import com.shop.user.model.dao.User;
import com.shop.user.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToDao(UserDto userDto);

    @Mapping(target = "password", ignore = true)
    UserDto daoToDto(User user);
}
