package com.edenlifemock;

import com.edenlifemock.clients.models.Payment;

import java.util.List;

public interface PaymentService {
    String pay();

    void retryPayments(List<Payment> payment);
}
