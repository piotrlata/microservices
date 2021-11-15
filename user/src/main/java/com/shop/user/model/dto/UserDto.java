package com.shop.user.model.dto;

import com.shop.user.validator.PasswordValidator;
import com.shop.user.validator.group.CreateUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordValidator(groups = CreateUser.class)
public class UserDto {
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
