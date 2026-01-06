package com.thiep.thiep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderResponse {
    private Long orderId;
    private String publicCode;
}
