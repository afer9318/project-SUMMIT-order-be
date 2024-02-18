package com.B2B.SP.order.repository;

import com.B2B.SP.order.dto.OrderItemDto;
import com.B2B.SP.order.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT i from OrderItem i WHERE i.order.orderId = :orderId")
    List<OrderItem> findAllByOrder(@Param("orderId") Long orderId);
}
