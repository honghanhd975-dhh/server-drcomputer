package com.thiep.thiep.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SaveContentRequest {
    @NotBlank
    private String contentJson; // bạn gửi nguyên JSON string
}
