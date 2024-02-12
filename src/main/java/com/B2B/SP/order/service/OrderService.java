package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> findAll();

    OrderDto findById(Long orderId);
}
