package com.shop.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto<T> {
    private String emailReceiver;
    private String templateName;
    private T variables;
}