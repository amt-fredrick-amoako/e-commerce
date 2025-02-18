package com.amalitech.ecommerce.payment;

import com.amalitech.ecommerce.dtos.PaymentRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(@Valid PaymentRequest paymentRequest) {
        return Payment.builder()
                .id(paymentRequest.orderId())
                .orderId(paymentRequest.orderId())
                .paymentMethod(paymentRequest.paymentMethod())
                .amount(paymentRequest.amount())
                .build();
    }
}
