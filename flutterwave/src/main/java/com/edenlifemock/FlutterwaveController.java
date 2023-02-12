package com.edenlifemock;

import com.edenlifemock.clients.PaymentRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

@AllArgsConstructor
@Slf4j
public class FlutterwaveController {
    private final FlutterwaveService flutterwaveService;


    @PostMapping("/")
    String pay(@RequestBody PaymentRequest request) {
        log.info("inside flutterwave endpoint");
        return flutterwaveService.pay(request);
    }

}
