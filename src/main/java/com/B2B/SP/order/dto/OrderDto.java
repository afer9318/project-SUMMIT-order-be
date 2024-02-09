package com.B2B.SP.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long orderId;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Supplier ID cannot be null")
    private Long supplierId;

    @NotNull(message = "Delivery address cannot be null")
    private String deliveryAddress;

    @NotNull(message = "Delivery date cannot be null")
    private LocalDate deliveryDate;

    private Boolean activeOrder = Boolean.TRUE;
}
