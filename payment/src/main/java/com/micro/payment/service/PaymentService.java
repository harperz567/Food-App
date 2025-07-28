package com.micro.payment.service;

import com.micro.payment.dto.PaymentRequestDTO;
import com.micro.payment.dto.PaymentResponseDTO;
import com.micro.payment.entity.Payment;
import com.micro.payment.repo.PaymentRepo;
import com.stripe.model.PaymentIntent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;

    @Autowired
    private StripeService stripeService;

    public PaymentResponseDTO createPaymentIntent(PaymentRequestDTO paymentRequest) {
        try {
            // Create PaymentIntent with Stripe
            PaymentIntent paymentIntent = stripeService.createPaymentIntent(paymentRequest.getAmount());

            // Save payment record in database
            Payment payment = new Payment();
            payment.setOrderId(paymentRequest.getOrderId());
            payment.setUserId(paymentRequest.getUserId());
            payment.setAmount(paymentRequest.getAmount());
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            payment.setPaymentStatus("PENDING");
            payment.setStripePaymentIntentId(paymentIntent.getId());

            Payment savedPayment = paymentRepo.save(payment);

            return new PaymentResponseDTO(
                savedPayment.getPaymentId(),
                "PENDING",
                paymentIntent.getClientSecret(),
                "Payment intent created successfully",
                savedPayment.getAmount()
            );

        } catch (Exception e) {
            return new PaymentResponseDTO(
                null,
                "FAILED",
                null,
                "Failed to create payment intent: " + e.getMessage(),
                paymentRequest.getAmount()
            );
        }
    }

    public PaymentResponseDTO getPaymentStatus(Long paymentId) {
        try {
            Optional<Payment> paymentOptional = paymentRepo.findById(paymentId);

            if (paymentOptional.isEmpty()) {
                return new PaymentResponseDTO(
                    paymentId,
                    "NOT_FOUND",
                    null,
                    "Payment not found",
                    null
                );
            }

            Payment payment = paymentOptional.get();

            // Get latest status from Stripe
            PaymentIntent paymentIntent = stripeService.retrievePaymentIntent(payment.getStripePaymentIntentId());

            String status = "PENDING";
            if ("succeeded".equals(paymentIntent.getStatus())) {
                status = "COMPLETED";
            } else if ("canceled".equals(paymentIntent.getStatus()) || "payment_failed".equals(paymentIntent.getStatus())) {
                status = "FAILED";
            }

            // Update local status if changed
            if (!status.equals(payment.getPaymentStatus())) {
                payment.setPaymentStatus(status);
                paymentRepo.save(payment);
            }

            return new PaymentResponseDTO(
                payment.getPaymentId(),
                status,
                null,
                "Payment status retrieved",
                payment.getAmount()
            );

        } catch (Exception e) {
            return new PaymentResponseDTO(
                paymentId,
                "FAILED",
                null,
                "Failed to get payment status: " + e.getMessage(),
                null
            );
        }
    }
}