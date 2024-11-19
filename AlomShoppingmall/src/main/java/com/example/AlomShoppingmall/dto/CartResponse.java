package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.Cart;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
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

}
