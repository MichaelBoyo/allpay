package com.tbthecoder;

import com.edenlifemock.clients.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PayStackController {
    private final PayStackService payStackService;
     @PostMapping("/")
    String pay(@RequestBody PaymentRequest request) {
        log.info("inside paystack endpoint");
        return payStackService.pay(request);
    }
}
