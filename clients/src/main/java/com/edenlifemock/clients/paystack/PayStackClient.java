package com.edenlifemock.clients.paystack;

import com.edenlifemock.clients.CustomClient;
import com.edenlifemock.clients.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("paystack")
public interface PayStackClient extends CustomClient {
    @PostMapping("/")
    String pay(@RequestBody PaymentRequest request);
}
