package com.shop.user.mapper;

import com.shop.common.model.dto.AddressDto;
import com.shop.user.model.dao.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper extends AuditingMapper<Address, AddressDto> {
    Address dtoToDao(AddressDto addressDto);

    AddressDto daoToDto(Address address);
}
