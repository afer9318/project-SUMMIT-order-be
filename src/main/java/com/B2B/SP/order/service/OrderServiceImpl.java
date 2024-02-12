package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderDto;
import com.B2B.SP.order.exceptions.customexceptions.OrderNotFoundException;
import com.B2B.SP.order.mapper.OrderMapper;
import com.B2B.SP.order.model.Order;
import com.B2B.SP.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;

    // constructor
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        try{
            logger.info("Finding all active orders");

            return orderRepository.findAllActiveOrders()
                    .stream()
                    .map(OrderMapper.INSTANCE::orderToDTO)
                    .collect(Collectors.toList());

        }catch (Exception e){
            logger.error("Exception while finding all active orders", e);
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public OrderDto findById(Long orderId) {
        try{
            logger.info("Finding order by id: {}", orderId);

            Order order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new OrderNotFoundException("Order nou found with id: " + orderId));

            return OrderMapper.INSTANCE.orderToDTO(order);
        }catch (Exception e){
            logger.error("Exception while finding order by id: {}",orderId, e);
            throw e;
        }
    }
}
