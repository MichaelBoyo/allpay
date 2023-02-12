package com.edenlifemock;

import com.edenlifemock.clients.models.Payment;
import com.edenlifemock.clients.models.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.LinkOption;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RetryPayment {
    private final PaymentService paymentService;
    private final MongoTemplate mongoTemplate;

    @Scheduled(fixedDelay = 3000, initialDelay = 1000) /*Fixed delay: Five minutes*/
    public void retryPayment() {

        List<Payment> payments = mongoTemplate
                .findAll(Payment.class, "payments");
        payments = payments.stream().filter(payment -> payment.getPayTrial().getStatus().equals(PaymentStatus.FAIL)).toList();
        paymentService.retryPayments(payments);

        log.info("failed payments => {}", payments);

        if (payments.isEmpty()) {
            log.info("no pending payments");
        }

    }
}
