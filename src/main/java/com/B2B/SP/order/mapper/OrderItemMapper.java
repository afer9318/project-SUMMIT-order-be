package com.B2B.SP.order.mapper;

import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface OrderItemMapper {

    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(source = "order.orderId", target = "orderId")
    OrderItemDto orderItemToDTO(OrderItem orderItem);

    @Mapping(source = "orderId", target = "order.orderId")
    OrderItem dtoToOrderItem(OrderItemDto orderItemDto);

    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(source = "orderId", target = "order.orderId")
    OrderItem dtoToOrderItemSave(OrderItemDto orderItemDto);
}
