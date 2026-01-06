package com.thiep.thiep.controller;

import com.thiep.thiep.entity.CardAsset;
import com.thiep.thiep.entity.CardContent;
import com.thiep.thiep.entity.CardOrder;
import com.thiep.thiep.repository.CardAssetRepository;
import com.thiep.thiep.repository.CardContentRepository;
import com.thiep.thiep.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicController {

    private final OrderService orderService;
    private final CardContentRepository contentRepo;
    private final CardAssetRepository assetRepo;

    public PublicController(OrderService orderService, CardContentRepository contentRepo, CardAssetRepository assetRepo) {
        this.orderService = orderService;
        this.contentRepo = contentRepo;
        this.assetRepo = assetRepo;
    }

    @GetMapping("/{publicCode}")
    public Map<String, Object> getPublic(@PathVariable String publicCode) {
        CardOrder order = orderService.getByPublicCode(publicCode);
        CardContent content = contentRepo.findById(order.getId()).orElse(null);
        List<CardAsset> assets = assetRepo.findByOrderIdOrderBySortIndexAsc(order.getId());

        Map<String, Object> res = new HashMap<>();
        res.put("order", order);
        res.put("template", order.getTemplate());
        res.put("content", content == null ? null : content.getContentJson());
        res.put("assets", assets);
        return res;
    }
}
