package com.shop.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto extends AuditableDto {
    private Long id;
    @NotBlank
    private String country;
    @NotBlank
    private String town;
    @NotBlank
    private String street;
    @NotBlank
    private String postcode;
}
