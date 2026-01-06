package com.thiep.thiep.controller;

import com.thiep.thiep.entity.Payment;
import com.thiep.thiep.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // checkout: tạo payment INITIATED và chuyển order -> PENDING_PAYMENT
    @PostMapping("/checkout")
    public Payment checkout(@RequestBody Map<String, Object> body) {
        Long orderId = Long.valueOf(body.get("orderId").toString());
        return service.initiate(orderId);
    }

    // dev: đánh dấu trả tiền thành công
    @PostMapping("/{paymentId}/success")
    public Payment markSuccess(@PathVariable Long paymentId, @RequestBody Map<String, Object> body) {
        String txn = body.getOrDefault("transactionCode", "DEV_TXN_" + paymentId).toString();
        return service.markSuccess(paymentId, txn);
    }

    // lịch sử thanh toán theo order
    @GetMapping
    public List<Payment> listByOrder(@RequestParam Long orderId) {
        return service.listByOrder(orderId);
    }
}
