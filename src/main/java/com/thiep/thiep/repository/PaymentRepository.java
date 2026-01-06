package com.thiep.thiep.repository;

import com.thiep.thiep.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByOrderIdOrderByCreatedAtDesc(Long orderId);
    @Query("select coalesce(sum(p.amount),0) from Payment p where p.status='SUCCESS'")
    Long sumRevenueSuccess();
}