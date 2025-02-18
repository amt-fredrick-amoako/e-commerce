package com.amalitech.ecommerce.services;

import com.amalitech.ecommerce.dtos.OrderLineRequest;
import com.amalitech.ecommerce.dtos.OrderLineResponse;
import com.amalitech.ecommerce.mappers.OrderLineMapper;
import com.amalitech.ecommerce.repositories.OrderLineRepository;
import com.amalitech.ecommerce.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = orderLineMapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrderLineResponse> findAllByOrderId(Integer id) {
        return orderLineRepository.findAllByOrderId(id)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .toList();
    }
}
