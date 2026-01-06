package com.thiep.thiep.entity;

import com.thiep.thiep.model.AssetType;
import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "card_asset")
@Getter @Setter
public class CardAsset {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private CardOrder order;

    @Enumerated(EnumType.STRING)
    private AssetType type;

    private String fieldKey;

    @Column(nullable = false, length = 800)
    private String url;

    private Integer sortIndex;

    private LocalDateTime createdAt;
}
