package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItemDto> findAllByOrder(Long orderId);

    List<OrderItemDto> saveOrderItems(List<OrderItemDto> orderItemDTOList);
}
