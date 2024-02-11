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

    OrderItemDto orderItemToDTO(OrderItem orderItem);

    OrderItem dtoToOrderItem(OrderItemDto orderItemDto);

    @Mapping(target = "orderItemId", ignore = true)
    OrderItem dtoToOrderItemSave(OrderItemDto orderItemDto);
}
