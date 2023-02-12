package com.edenlifemock.clients;

import com.edenlifemock.clients.models.PayTrial;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentRequest {
    private String id;
    private PayTrial payTrial;

    public PaymentRequest(PayTrial payTrial, String id) {
        this.payTrial = payTrial;
        this.id = id;
    }

}
