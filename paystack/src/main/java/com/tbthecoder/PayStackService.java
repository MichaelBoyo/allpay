package com.tbthecoder;

import com.edenlifemock.clients.PaymentRequest;
import com.edenlifemock.clients.models.PayTrial;
import com.edenlifemock.clients.models.Payment;
import com.edenlifemock.clients.models.PaymentGateway;
import com.edenlifemock.clients.models.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoAction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayStackService {
    private final MongoTemplate template;

    public String pay(PaymentRequest request) {
        Payment payment = template
                .findOne(Query.query(Criteria.where("id").is(request.getId())), Payment.class, "payments");
        assert payment != null;
        payment.setPayTrial(PayTrial.builder()
                .status(PaymentStatus.PAID)
                .paymentGateway(PaymentGateway.PAYSTACK)
                .build());
        template.save(payment, "payments");
        return "payment in progress";
    }
}
