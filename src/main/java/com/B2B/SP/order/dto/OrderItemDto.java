package com.B2B.SP.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long orderItemId;
    private OrderDto orderDto;
    private Long productId;
    private Long productQuantity;
}
