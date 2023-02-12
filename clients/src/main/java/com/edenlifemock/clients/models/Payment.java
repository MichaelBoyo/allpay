package com.edenlifemock.clients.models;

import com.edenlifemock.clients.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private String id;
    private PayTrial payTrial;

    private List<PayTrial> failedPayments = new ArrayList<>();

    public PaymentRequest toPaymentRequest() {
        return new PaymentRequest(payTrial, id);
    }
}
