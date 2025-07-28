package com.micro.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDTO {
    private Long paymentId;
    private String paymentStatus;
    private String clientSecret; // For Stripe frontend integration
    private String message;
    private Double amount;
}