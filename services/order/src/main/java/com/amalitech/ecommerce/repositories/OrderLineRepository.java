package com.amalitech.ecommerce.repositories;

import com.amalitech.ecommerce.orderline.OrderLine;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(Integer orderId);
}
