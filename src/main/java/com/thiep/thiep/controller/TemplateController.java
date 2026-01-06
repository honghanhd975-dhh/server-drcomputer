package com.thiep.thiep.controller;

import com.thiep.thiep.entity.CardTemplate;
import com.thiep.thiep.service.TemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin
public class TemplateController {

    private final TemplateService service;

    public TemplateController(TemplateService service) {
        this.service = service;
    }

    @GetMapping
    public List<CardTemplate> list() {
        return service.listActive();
    }

    @GetMapping("/{id}")
    public CardTemplate detail(@PathVariable Long id) {
        return service.getById(id);
    }
}
