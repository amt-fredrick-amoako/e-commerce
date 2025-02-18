package com.amalitech.ecommerce.services;

import com.amalitech.ecommerce.clients.CustomerClient;
import com.amalitech.ecommerce.clients.PaymentClient;
import com.amalitech.ecommerce.clients.ProductClient;
import com.amalitech.ecommerce.dtos.*;
import com.amalitech.ecommerce.exceptions.BusinessException;
import com.amalitech.ecommerce.kafka.OrderConfirmation;
import com.amalitech.ecommerce.kafka.OrderProducer;
import com.amalitech.ecommerce.mappers.OrderMapper;
import com.amalitech.ecommerce.repositories.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(@Valid OrderRequest request) {
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the specified ID"));
        List<PurchaseResponse> purchasedProducts = productClient.purchaseProducts(request.products());
        var order = orderRepository.save(orderMapper.toOrder(request));
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.id(),
                            purchaseRequest.quantity()
                    )
            );
        }

        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        paymentClient.requestOrderPayment(paymentRequest);
        
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
