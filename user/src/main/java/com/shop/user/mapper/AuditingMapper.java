package com.shop.user.mapper;

import com.shop.common.model.dao.Auditable;
import com.shop.common.model.dto.AuditableDto;
import com.shop.security.SecurityUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

public interface AuditingMapper<DAO extends Auditable, DTO extends AuditableDto> {
    @AfterMapping
    default void mapAuditingForAdmin(DAO dao, @MappingTarget AuditableDto.AuditableDtoBuilder<?, ?> dto) {
        if (!SecurityUtils.hasRole("ROLE_ADMIN")) {
            dto.createdBy(null);
            dto.createdDate(null);
            dto.lastModifiedBy(null);
            dto.lastModifiedDate(null);
        }
    }
}
