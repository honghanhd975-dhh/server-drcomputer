package com.thiep.thiep.controller;

import com.thiep.thiep.dto.*;
import com.thiep.thiep.entity.CardAsset;
import com.thiep.thiep.entity.CardOrder;
import com.thiep.thiep.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public CreateOrderResponse create(@Valid @RequestBody CreateOrderRequest req) {
        CardOrder o = service.createOrder(req);
        return new CreateOrderResponse(o.getId(), o.getPublicCode());
    }

    @PutMapping("/{id}/content")
    public void saveContent(@PathVariable Long id, @Valid @RequestBody SaveContentRequest req) {
        service.saveContent(id, req.getContentJson());
    }

    @PostMapping("/{id}/assets")
    public CardAsset addAsset(@PathVariable Long id, @Valid @RequestBody AddAssetRequest req) {
        return service.addAsset(id, req);
    }

    @GetMapping("/{id}")
    public CardOrder get(@PathVariable Long id) {
        return service.getOrder(id);
    }
}
