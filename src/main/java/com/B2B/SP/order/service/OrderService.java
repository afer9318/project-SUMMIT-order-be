package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderDto;
import com.B2B.SP.order.model.Order;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAll();

    OrderDto findById(Long orderId);

    OrderDto save(OrderDto orderDto);

    OrderDto update(OrderDto orderDto);
}
