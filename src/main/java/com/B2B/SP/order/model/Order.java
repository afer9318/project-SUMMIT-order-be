package com.B2B.SP.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    @Column(name = "active_order")
    private Boolean activeOrder = Boolean.TRUE;
}


