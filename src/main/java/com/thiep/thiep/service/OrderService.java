package com.thiep.thiep.service;

import com.thiep.thiep.dto.AddAssetRequest;
import com.thiep.thiep.dto.CreateOrderRequest;
import com.thiep.thiep.entity.*;
import com.thiep.thiep.model.OrderStatus;
import com.thiep.thiep.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class OrderService {

    private final CardTemplateRepository templateRepo;
    private final CardOrderRepository orderRepo;
    private final CardContentRepository contentRepo;
    private final CardAssetRepository assetRepo;

    public OrderService(CardTemplateRepository templateRepo,
                        CardOrderRepository orderRepo,
                        CardContentRepository contentRepo,
                        CardAssetRepository assetRepo) {
        this.templateRepo = templateRepo;
        this.orderRepo = orderRepo;
        this.contentRepo = contentRepo;
        this.assetRepo = assetRepo;
    }

    @Transactional
    public CardOrder createOrder(CreateOrderRequest req) {
        CardTemplate template = templateRepo.findById(req.getTemplateId())
                .orElseThrow(() -> new RuntimeException("Template not found"));

        CardOrder o = new CardOrder();
        o.setTemplate(template);
        o.setCustomerName(req.getCustomerName());
        o.setCustomerEmail(req.getCustomerEmail());
        o.setPublicCode(PublicCodeUtil.gen(12));
        o.setStatus(OrderStatus.DRAFT);
        o.setTotalAmount(template.getBasePrice() == null ? 0 : template.getBasePrice());
        o.setCreatedAt(LocalDateTime.now());
        return orderRepo.save(o);
    }

    @Transactional
    public void saveContent(Long orderId, String contentJson) {
        CardOrder order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CardContent content = contentRepo.findById(orderId).orElse(null);
        if (content == null) {
            content = new CardContent();
            content.setOrder(order);
        }
        content.setContentJson(contentJson);
        contentRepo.save(content);
    }

    @Transactional
    public CardAsset addAsset(Long orderId, AddAssetRequest req) {
        CardOrder order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        CardAsset a = new CardAsset();
        a.setOrder(order);
        a.setType(req.getType());
        a.setFieldKey(req.getFieldKey());
        a.setUrl(req.getUrl());
        a.setSortIndex(req.getSortIndex() == null ? 0 : req.getSortIndex());
        a.setCreatedAt(LocalDateTime.now());
        return assetRepo.save(a);
    }

    public CardOrder getOrder(Long id) {
        return orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public CardOrder getByPublicCode(String code) {
        return orderRepo.findByPublicCode(code).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
