package com.amalitech.ecommerce.mappers;

import com.amalitech.ecommerce.dtos.OrderRequest;
import com.amalitech.ecommerce.dtos.OrderResponse;
import com.amalitech.ecommerce.order.Order;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(@Valid OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customerId(request.customerId())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}
