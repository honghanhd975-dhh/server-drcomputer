package com.thiep.thiep.entity;

import jakarta.persistence.*;
import lombok.Getter; import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "card_template")
@Getter @Setter
public class CardTemplate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String templateKey;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String thumbnailUrl;
    private String demoUrl;

    @Column(columnDefinition = "JSON")
    private String manifestJson;

    private String folderPath;

    private Integer basePrice;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private LocalDateTime createdAt;

    public enum Status { ACTIVE, INACTIVE }
}
