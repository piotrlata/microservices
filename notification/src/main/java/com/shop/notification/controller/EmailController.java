package com.shop.notification.controller;

import com.shop.common.model.dto.EmailDto;
import com.shop.notification.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
@Slf4j
public class EmailController {
    private final EmailService emailService;

    @PostMapping
    public void sendEmail(@RequestBody EmailDto<Map<String, Object>> emailDto) {
        log.info("{}", emailDto);
        emailService.sendEmail(emailDto.getEmailReceiver(), emailDto.getTemplateName(), emailDto.getVariables());
    }
}
