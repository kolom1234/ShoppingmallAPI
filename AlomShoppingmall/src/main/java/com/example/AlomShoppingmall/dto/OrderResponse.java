package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.Order;

import java.time.LocalDateTime;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

