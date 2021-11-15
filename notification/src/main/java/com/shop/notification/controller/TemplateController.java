package com.shop.notification.controller;

import com.shop.notification.mapper.TemplateMapper;
import com.shop.notification.model.dao.Template;
import com.shop.notification.model.dto.TemplateDto;
import com.shop.notification.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class TemplateController {
    private final TemplateService templateService;
    private final TemplateMapper templateMapper;

    @PostMapping
    public TemplateDto saveTemplate(@RequestBody TemplateDto template) {
        return templateMapper.daoToDto(templateService.save(templateMapper.dtoToDao(template)));
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable Long id) {
        templateService.delete(id);
    }

    @PutMapping("/{id}")
    public TemplateDto updateTemplate(@RequestBody TemplateDto template, @PathVariable Long id) {
        return templateMapper.daoToDto(templateService.update(templateMapper.dtoToDao(template), id));
    }

    @GetMapping("/{id}")
    public TemplateDto templateById(@PathVariable Long id) {
        return templateMapper.daoToDto(templateService.findTemplateById(id));
    }
}
