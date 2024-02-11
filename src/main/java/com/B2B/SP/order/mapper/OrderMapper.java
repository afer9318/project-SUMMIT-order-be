package com.B2B.SP.order.mapper;

import com.B2B.SP.order.dto.OrderDto;
import com.B2B.SP.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto orderToDTO(Order order);

    Order dtoToOrder(OrderDto orderDto);

    @Mapping(target = "orderId", ignore = true)
    Order dtoToOrderSave(OrderDto orderDto);
}
