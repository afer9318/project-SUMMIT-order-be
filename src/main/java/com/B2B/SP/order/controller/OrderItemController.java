package com.B2B.SP.order.controller;

import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.service.OrderItemService;
import com.B2B.SP.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderItem")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItemDto>> findAllByOrder(@PathVariable Long orderId){
        List<OrderItemDto> orderItemList= orderItemService.findAllByOrder(orderId);
        return ResponseEntity.ok(orderItemList);
    }
}
