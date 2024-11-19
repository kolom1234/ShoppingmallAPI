package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class OrderResponse {
    private Long id;
    private LocalDateTime orderAt;
    private String status;
    private Integer quantity;
    private Long userId;
    private Long productId;

    // 생성자와 getter
    public OrderResponse(Order order) {
        this.id = order.getId();
        this.orderAt = order.getOrderAt();
        this.status = order.getStatus();
        this.quantity = order.getQuantity();
        this.userId = order.getUser().getId();
        this.productId = order.getProduct().getId();
    }
}

