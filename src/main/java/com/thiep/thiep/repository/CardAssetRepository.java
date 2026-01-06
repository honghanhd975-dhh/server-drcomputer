package com.thiep.thiep.repository;

import com.thiep.thiep.entity.CardAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardAssetRepository extends JpaRepository<CardAsset, Long> {
    List<CardAsset> findByOrderIdOrderBySortIndexAsc(Long orderId);
}