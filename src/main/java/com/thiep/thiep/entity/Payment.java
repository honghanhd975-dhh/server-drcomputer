package com.thiep.thiep.entity;

import com.thiep.thiep.model.PaymentProvider;
import com.thiep.thiep.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter @Setter
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private CardOrder order;

    @Enumerated(EnumType.STRING)
    private PaymentProvider provider = PaymentProvider.MANUAL;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.INITIATED;

    private String transactionCode;

    private LocalDateTime paidAt;
    private LocalDateTime createdAt;
}
