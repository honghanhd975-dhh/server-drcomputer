package com.thiep.thiep.controller;

import com.thiep.thiep.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/revenue")
@CrossOrigin
public class AdminRevenueController {

    private final PaymentService paymentService;

    public AdminRevenueController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        return Map.of("totalRevenueSuccess", paymentService.revenueSuccessTotal());
    }
}
