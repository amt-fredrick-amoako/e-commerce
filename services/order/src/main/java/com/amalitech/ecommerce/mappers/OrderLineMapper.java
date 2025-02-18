package com.amalitech.ecommerce.mappers;

import com.amalitech.ecommerce.dtos.OrderLineRequest;
import com.amalitech.ecommerce.dtos.OrderLineResponse;
import com.amalitech.ecommerce.order.Order;
import com.amalitech.ecommerce.orderline.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.orderId())
                .quantity(orderLineRequest.quantity())
                .order(Order.builder()
                        .id(orderLineRequest.orderId())
                        .build())
                .productId(orderLineRequest.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
