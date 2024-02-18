package com.B2B.SP.order.controller;

import com.B2B.SP.order.dto.OrderDto;
import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.model.OrderItem;
import com.B2B.SP.order.service.OrderItemService;
import com.B2B.SP.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        List<OrderItemDto> orderItemDTOList= orderItemService.findAllByOrder(orderId);
        return ResponseEntity.ok(orderItemDTOList);
    }

    @PostMapping("/")
    public ResponseEntity<List<OrderItemDto>> saveOrderItems(@Validated @RequestBody List<OrderItemDto> orderItemDTOList){
        List<OrderItemDto> savedOrderItemDtoList = orderItemService.saveOrderItems(orderItemDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderItemDtoList);
    }
}
