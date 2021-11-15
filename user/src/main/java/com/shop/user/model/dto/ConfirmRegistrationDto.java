package com.shop.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmRegistrationDto {
    public String firstName;
    public String lastName;
    public String link;
}
