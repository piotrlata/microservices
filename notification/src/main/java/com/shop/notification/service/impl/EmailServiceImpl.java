package com.shop.notification.service.impl;

import com.shop.notification.model.NotificationStatus;
import com.shop.notification.model.dao.NotificationHistory;
import com.shop.notification.model.dao.Template;
import com.shop.notification.repository.NotificationHistoryRepository;
import com.shop.notification.service.EmailService;
import com.shop.notification.service.TemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final ITemplateEngine iTemplateEngine;
    private final JavaMailSender javaMailSender;
    private final TemplateService templateService;
    private final NotificationHistoryRepository notificationHistoryRepository;

    @Override
    @Async
    public void sendEmail(String emailReceiver, String templateName, Map<String, Object> variables) {
        Template template = templateService.findByName(templateName);
        Context context = new Context(Locale.forLanguageTag("pl"), variables);
        String body = iTemplateEngine.process(template.getBody(), context);
        NotificationHistory notificationHistory = notificationHistoryRepository.save(NotificationHistory.builder()
                .body(body)
                .receiver(emailReceiver)
                .subject(template.getSubject())
                .status(NotificationStatus.PENDING)
                .build());
        try {
            javaMailSender.send(message -> {
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setTo(emailReceiver);
                helper.setFrom("emailtestowy64@gmail.com");
                helper.setSubject(template.getSubject());
                helper.setText(body, true);
            });
            notificationHistory.setStatus(NotificationStatus.SUCCESS);
        } catch (Exception e) {
            notificationHistory.setStatus(NotificationStatus.ERROR);
            log.error("error during sending email", e);
        }
        notificationHistoryRepository.save(notificationHistory);
    }
}
