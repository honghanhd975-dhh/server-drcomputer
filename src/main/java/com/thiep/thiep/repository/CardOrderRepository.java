package com.thiep.thiep.repository;

import com.thiep.thiep.entity.CardOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardOrderRepository extends JpaRepository<CardOrder, Long> {
    Optional<CardOrder> findByPublicCode(String publicCode);
    List<CardOrder> findByCustomerEmailOrderByCreatedAtDesc(String email);
    List<CardOrder> findByUserIdOrderByCreatedAtDesc(Long userId);
}

