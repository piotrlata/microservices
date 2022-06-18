package com.shop.common.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto extends AuditableDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @Min(value = 1, message = "price cannot be less than 1")
    private Double price;
    @Min(value = 1, message = "quantity cannot be less than 1")
    private Integer quantity;
    private Integer revisionNumber;
}
