package com.B2B.SP.order.controller;

import com.B2B.SP.order.dto.OrderDto;
import com.B2B.SP.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<OrderDto>> findAllOrders(){
        List<OrderDto> orderList = orderService.findAll();
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId){
        OrderDto orderDto = orderService.findById(orderId);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping("/")
    public ResponseEntity<OrderDto> saveUser(@Validated @RequestBody OrderDto orderDto){
        OrderDto savedOrderDto = orderService.save(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderDto);
    }

    @PutMapping("/")
    public ResponseEntity<OrderDto> updateOrder(@Validated @RequestBody OrderDto orderDto){
        OrderDto updatedOrderDto = orderService.update(orderDto);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId){
        orderService.deleteById(orderId);
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
