package com.B2B.SP.order.service;

import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.exceptions.customexceptions.BadRequestException;
import com.B2B.SP.order.exceptions.customexceptions.OrderItemNotFoundException;
import com.B2B.SP.order.mapper.OrderItemMapper;
import com.B2B.SP.order.model.OrderItem;
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

            List<OrderItem> orderItemList = orderItemsRepository.findAllByOrder(orderId);

            if (orderItemList.isEmpty()){
                throw new OrderItemNotFoundException("No order items found for order: " + orderId);
            }

            return orderItemList.stream()
                    .map(OrderItemMapper.INSTANCE::orderItemToDTO)
                    .collect(Collectors.toList());

        }catch (Exception e){
            logger.error("Exception while finding all order items", e);
            throw e;
        }
    }

    @Override
    @Transactional
    public List<OrderItemDto> saveOrderItems(List<OrderItemDto> orderItemDTOList) {
        try{

            if (orderItemDTOList.stream().anyMatch(orderItemDto -> orderItemDto.getOrderItemId() != null)){
                throw new BadRequestException("Order items should not have an order item id");
            }

            if (orderItemDTOList.isEmpty() || orderItemDTOList.stream().map(OrderItemDto::getOrderId).distinct().count() > 1){
                throw new BadRequestException("All order items must have same order Id");
            }

            List<OrderItem> orderItemList = orderItemDTOList.stream()
                    .map(OrderItemMapper.INSTANCE::dtoToOrderItemSave)
                    .toList();

            List<OrderItem> savedOrderItemList = orderItemsRepository.saveAll(orderItemList);

            return savedOrderItemList.stream()
                    .map(OrderItemMapper.INSTANCE::orderItemToDTO)
                    .collect(Collectors.toList());

        }catch(Exception e){
            logger.error("Exception while saving order items", e);
            throw e;
        }
    }
}
