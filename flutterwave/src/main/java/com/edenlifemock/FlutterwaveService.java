package com.edenlifemock;

import com.edenlifemock.clients.PaymentRequest;
import com.edenlifemock.clients.models.PayTrial;
import com.edenlifemock.clients.models.Payment;
import com.edenlifemock.clients.models.PaymentGateway;
import com.edenlifemock.clients.models.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FlutterwaveService {
    private final MongoTemplate template;
    private final List<Integer> statusCodes = List.of(200, 404, 203);

    public String pay(PaymentRequest request) {
        Payment payment = new Payment();

        int code = flutterwaveApi();
        switch (code) {
            case 200 -> paymentSuccess(payment);
            case 404 -> paymentFailed(payment);
            case 203 -> paymentPending(payment);
        }
        template.save(payment, "payments");
        return "payment in progress";
    }

    private void paymentPending(Payment payment) {
        payment.setPayTrial(PayTrial.builder()
                .status(PaymentStatus.PENDING)
                .paymentGateway(PaymentGateway.FLUTTERWAVE)
                .build());
    }

    private void paymentFailed(Payment payment) {
        PayTrial payTrial = PayTrial.builder()
                .status(PaymentStatus.FAIL)
                .paymentGateway(PaymentGateway.FLUTTERWAVE)
                .build();
        payment.setPayTrial(payTrial);

        payment.getFailedPayments().add(payTrial);
    }

    private void paymentSuccess(Payment payment) {
        payment.setPayTrial(PayTrial.builder()
                .status(PaymentStatus.PAID)
                .paymentGateway(PaymentGateway.FLUTTERWAVE)
                .build());
    }


    private int flutterwaveApi() {
        Random random = new Random();
        return statusCodes.get(random.nextInt(0, 3));
    }

}
