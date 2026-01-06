package com.thiep.thiep.service;

import com.thiep.thiep.entity.CardOrder;
import com.thiep.thiep.entity.Payment;
import com.thiep.thiep.model.OrderStatus;
import com.thiep.thiep.model.PaymentProvider;
import com.thiep.thiep.model.PaymentStatus;
import com.thiep.thiep.repository.CardOrderRepository;
import com.thiep.thiep.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    private final CardOrderRepository orderRepo;
    private final PaymentRepository paymentRepo;

    public PaymentService(CardOrderRepository orderRepo, PaymentRepository paymentRepo) {
        this.orderRepo = orderRepo;
        this.paymentRepo = paymentRepo;
    }

    @Transactional
    public Payment initiate(Long orderId) {
        CardOrder order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        Payment p = new Payment();
        p.setOrder(order);
        p.setProvider(PaymentProvider.MANUAL);
        p.setAmount(order.getTotalAmount() == null ? 0 : order.getTotalAmount());
        p.setStatus(PaymentStatus.INITIATED);
        p.setCreatedAt(LocalDateTime.now());

        order.setStatus(OrderStatus.PENDING_PAYMENT);
        orderRepo.save(order);

        return paymentRepo.save(p);
    }

    @Transactional
    public Payment markSuccess(Long paymentId, String txn) {
        Payment p = paymentRepo.findById(paymentId).orElseThrow(() -> new RuntimeException("Payment not found"));
        p.setStatus(PaymentStatus.SUCCESS);
        p.setTransactionCode(txn);
        p.setPaidAt(LocalDateTime.now());
        paymentRepo.save(p);

        CardOrder order = p.getOrder();
        order.setStatus(OrderStatus.PAID);
        orderRepo.save(order);

        return p;
    }

    public List<Payment> listByOrder(Long orderId) {
        return paymentRepo.findByOrderIdOrderByCreatedAtDesc(orderId);
    }

    public Long revenueSuccessTotal() {
        return paymentRepo.sumRevenueSuccess();
    }
}
