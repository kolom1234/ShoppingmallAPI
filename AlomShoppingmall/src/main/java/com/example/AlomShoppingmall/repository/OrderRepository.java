package com.example.AlomShoppingmall.repository;

import com.example.AlomShoppingmall.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE " +
            "(:orderId is null OR o.id = :orderId) AND " +
            "(:orderDate is null OR o.orderAt = :orderDate) AND " +
            "(:userId is null OR o.user.id = :userId) AND " +
            "(:productId is null OR o.product.id = :productId)")
    List<Order> findOrders(
            @Param("orderId") Long orderId,
            @Param("orderDate") LocalDateTime orderDate,
            @Param("userId") Long userId,
            @Param("productId") Long productId
    );
}
