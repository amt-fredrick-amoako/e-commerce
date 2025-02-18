package com.amalitech.ecommerce.controller;

import com.amalitech.ecommerce.dtos.OrderRequest;
import com.amalitech.ecommerce.dtos.OrderResponse;
import com.amalitech.ecommerce.services.OrdersService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Integer> createOrder(
            @RequestBody @Valid OrderRequest request
    ){
        return ResponseEntity.ok(ordersService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Integer orderId
    ){
        return ResponseEntity.ok(ordersService.findById(orderId));
    }
}
