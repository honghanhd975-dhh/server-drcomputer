package com.thiep.thiep.entity;

import com.thiep.thiep.model.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "card_order")
@Getter @Setter
public class CardOrder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "template_id")
    private CardTemplate template;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String customerName;
    private String customerEmail;

    @Column(nullable = false, unique = true)
    private String publicCode;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.DRAFT;

    private Integer totalAmount;

    private LocalDateTime expireAt;
    private LocalDateTime createdAt;
}
