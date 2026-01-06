package com.thiep.thiep.service;

import com.thiep.thiep.entity.CardTemplate;
import com.thiep.thiep.repository.CardTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService {
    private final CardTemplateRepository repo;

    public TemplateService(CardTemplateRepository repo) {
        this.repo = repo;
    }

    public List<CardTemplate> listActive() {
        return repo.findByStatus(CardTemplate.Status.ACTIVE);
    }

    public CardTemplate getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Template not found"));
    }
}
