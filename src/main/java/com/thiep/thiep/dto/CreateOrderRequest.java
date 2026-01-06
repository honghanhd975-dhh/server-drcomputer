package com.thiep.thiep.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateOrderRequest {
    @NotNull
    private Long templateId;

    private String customerName;

    @Email
    private String customerEmail; // nếu chưa login
}
