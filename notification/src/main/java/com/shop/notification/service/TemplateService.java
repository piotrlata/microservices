package com.shop.notification.service;

import com.shop.notification.model.dao.Template;

public interface TemplateService {
    Template save(Template template);

    void delete(Long id);

    Template update(Template template, Long id);

    Template findTemplateById(Long id);

    Template findByName(String templateName);
}
