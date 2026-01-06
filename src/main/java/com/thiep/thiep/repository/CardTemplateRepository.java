package com.thiep.thiep.repository;

import com.thiep.thiep.entity.CardTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardTemplateRepository extends JpaRepository<CardTemplate, Long> {
    List<CardTemplate> findByStatus(CardTemplate.Status status);
}
