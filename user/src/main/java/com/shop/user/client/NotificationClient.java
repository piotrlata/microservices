package com.shop.user.client;

import com.shop.common.model.dto.EmailDto;
import com.shop.user.model.dto.ConfirmRegistrationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "NOTIFICATION-SERVICE")
@RequestMapping("/api/emails")
public interface NotificationClient {
    @PostMapping
    void sendEmail(@RequestBody EmailDto<ConfirmRegistrationDto> emailDto);
}