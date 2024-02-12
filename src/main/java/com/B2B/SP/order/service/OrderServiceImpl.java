package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderDto;
import com.B2B.SP.order.exceptions.customexceptions.BadRequestException;
import com.B2B.SP.order.exceptions.customexceptions.OrderNotFoundException;
import com.B2B.SP.order.mapper.OrderMapper;
import com.B2B.SP.order.model.Order;
import com.B2B.SP.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    @Override
    @Transactional
    public OrderDto save(OrderDto orderDto) {
        try{
            if (Objects.nonNull(orderDto.getOrderId())) {
                throw new BadRequestException("Saving order does not need an ID");
            }

            if(!orderDto.getActiveOrder()){
                throw new BadRequestException("Cannot create inactive orders");
            }

            logger.info("Saving order: {}", orderDto);
            Order order = OrderMapper.INSTANCE.dtoToOrder(orderDto);
            Order savedOrder = orderRepository.save(order);

            return OrderMapper.INSTANCE.orderToDTO(savedOrder);
        }catch (Exception e){
            logger.error("Exception while saving order", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public OrderDto update(OrderDto orderDto) {
        try{
            Long orderId = orderDto.getOrderId();

            if (orderId == null){
                logger.error("Cannot update order without id");
                throw new BadRequestException("Cannot update order without id");
            }

            if(!orderDto.getActiveOrder()){
                throw new BadRequestException("Cannot update order as inactive");
            }

            Optional<Order> optionalOrder = orderRepository.findByIdActiveOrder(orderId);

            if (optionalOrder.isEmpty()){
                logger.error("Cannot update deleted order");
                throw new BadRequestException("Cannot update deleted order");
            }

            Order updatedOrder = OrderMapper.INSTANCE.dtoToOrder(orderDto);
            updatedOrder = orderRepository.save(updatedOrder);
            return OrderMapper.INSTANCE.orderToDTO(updatedOrder);
        }catch (Exception e){
            logger.error("Exception while saving order", e);
            throw e;
        }
    }
}
