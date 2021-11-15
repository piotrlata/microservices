package com.shop.notification.mapper;

import com.shop.notification.model.dao.Template;
import com.shop.notification.model.dto.TemplateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    Template dtoToDao(TemplateDto templateDto);

    TemplateDto daoToDto(Template template);
}
