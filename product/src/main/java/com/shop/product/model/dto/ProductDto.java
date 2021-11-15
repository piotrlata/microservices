package com.shop.product.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Long id;
    @NotNull
    private String name;
    @Min(value = 1, message = "price cannot be less than 1")
    private String description;
    @NotNull
    private Double price;
    @Min(value = 1, message = "quantity cannot be less than 1")
    private Integer quantity;
    private Integer revisionNumber;
}