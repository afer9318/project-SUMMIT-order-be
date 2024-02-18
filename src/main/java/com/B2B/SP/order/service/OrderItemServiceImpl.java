package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.mapper.OrderItemMapper;
import com.B2B.SP.order.repository.OrderItemsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService{

    private final static Logger logger = LoggerFactory.getLogger(OrderItemServiceImpl.class);

    private final OrderItemsRepository orderItemsRepository;

    public OrderItemServiceImpl(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemDto> findAllByOrder(Long orderId) {
        try{
            logger.info("Finding all order items");

            return orderItemsRepository.findAllByOrder(orderId)
                    .stream()
                    .map(OrderItemMapper.INSTANCE::orderItemToDTO)
                    .collect(Collectors.toList());

        }catch (Exception e){
            logger.error("Exception while finding all order items", e);
            throw e;
        }
    }
}
