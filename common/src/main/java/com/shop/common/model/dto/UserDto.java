package com.shop.common.model.dto;

import com.shop.common.validator.PasswordValidator;
import com.shop.common.validator.group.CreateUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordValidator(groups = CreateUser.class)
public class UserDto extends AuditableDto {
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank(groups = CreateUser.class)
    private String password;
    @NotBlank(groups = CreateUser.class)
    private String confirmedPassword;
    private Integer revisionNumber;
    private AddressDto addressDto;
}
