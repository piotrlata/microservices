package com.shop.notification.model.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDto {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String subject;
    @Lob
    private String body;
}
