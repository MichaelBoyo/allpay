package com.edenlifemock;

import com.edenlifemock.clients.flutterwave.FlutterWaveClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pay")
@AllArgsConstructor
@Slf4j
public class WebController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> pay() {
        return ResponseEntity.ok(paymentService.pay());
    }



}
