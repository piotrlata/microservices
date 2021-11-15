package com.shop.user.mapper;

import com.shop.user.model.dao.Address;
import com.shop.user.model.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address dtoToDao(AddressDto addressDto);

    AddressDto daoToDto(Address address);
}
