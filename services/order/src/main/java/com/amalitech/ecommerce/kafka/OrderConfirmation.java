package com.amalitech.ecommerce.kafka;

import com.amalitech.ecommerce.dtos.CustomerResponse;
import com.amalitech.ecommerce.dtos.PurchaseResponse;
import com.amalitech.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
