package com.thiep.thiep.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "card_content")
@Getter @Setter
public class CardContent {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private CardOrder order;

    @Column(columnDefinition = "JSON", nullable = false)
    private String contentJson;

    private LocalDateTime updatedAt;
}
