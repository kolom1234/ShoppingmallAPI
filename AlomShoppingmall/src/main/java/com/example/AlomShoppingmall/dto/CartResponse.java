package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.Cart;

import java.time.LocalDateTime;

public class CartResponse {
    private Long id;
    private LocalDateTime createdAt;
    private Integer quantity;
    private Long userId;
    private Long productId;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.createdAt = cart.getCreatedAt();
        this.quantity = cart.getQuantity();
        this.userId = cart.getUser().getId();
        this.productId = cart.getProduct().getId();
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
