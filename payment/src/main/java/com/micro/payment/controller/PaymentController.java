package com.micro.payment.controller;

import com.micro.payment.dto.PaymentRequestDTO;
import com.micro.payment.dto.PaymentResponseDTO;
import com.micro.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createPaymentIntent")
    public ResponseEntity<PaymentResponseDTO> createPaymentIntent(@RequestBody PaymentRequestDTO paymentRequest) {
        PaymentResponseDTO response = paymentService.createPaymentIntent(paymentRequest);

        if ("FAILED".equals(response.getPaymentStatus())) {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/status/{paymentId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentStatus(@PathVariable Long paymentId) {
        PaymentResponseDTO response = paymentService.getPaymentStatus(paymentId);

        if ("NOT_FOUND".equals(response.getPaymentStatus())) {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}