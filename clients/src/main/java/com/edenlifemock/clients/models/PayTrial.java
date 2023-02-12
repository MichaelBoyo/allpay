package com.edenlifemock.clients.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayTrial {
    private PaymentGateway paymentGateway;
    private PaymentStatus status;
}
