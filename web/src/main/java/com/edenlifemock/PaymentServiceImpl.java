package com.edenlifemock;

import com.edenlifemock.clients.CustomClient;
import com.edenlifemock.clients.PaymentRequest;
import com.edenlifemock.clients.flutterwave.FlutterWaveClient;
import com.edenlifemock.clients.models.Payment;
import com.edenlifemock.clients.paystack.PayStackClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final FlutterWaveClient flutterWaveClient;
    private final PayStackClient payStackClient;


    private Map<String, CustomClient> services;
    private final List<String> serviceKeys = List.of("flutterwave", "paystack");

    @PostConstruct
    public void initServices() {
        services = new HashMap<>();
        services.put("flutterwave", flutterWaveClient);
        services.put("paystack", payStackClient);

    }

    @Override
    public String pay() {
        CustomClient client = services.get(serviceKeys.get(0));
        return client.pay(new PaymentRequest());
    }

    @Override
    @Async
    public void retryPayments(List<Payment> payments) {
        for (Payment payment : payments) {
            String serviceKey = serviceKeys.stream().filter(key -> !Objects.equals(key, payment
                    .getPayTrial().getPaymentGateway().toString().toLowerCase())).findFirst().orElseThrow(
                    () -> new RuntimeException("alll payment services tried but payment failure persists")
            );

            CustomClient client = services.get(serviceKey);
            client.pay(payment.toPaymentRequest());
        }
    }
}
