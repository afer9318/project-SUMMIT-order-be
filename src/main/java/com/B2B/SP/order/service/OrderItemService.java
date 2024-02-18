package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {

    List<OrderItemDto> findAllByOrder(Long orderId);
}
