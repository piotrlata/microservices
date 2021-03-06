package com.shop.product.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shop.common.model.dto.AuditableDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDto extends AuditableDto {
    private Long id;
    @NotBlank
    private String name;
    @NotEmpty
    private String description;
    private Integer revisionNumber;
}
