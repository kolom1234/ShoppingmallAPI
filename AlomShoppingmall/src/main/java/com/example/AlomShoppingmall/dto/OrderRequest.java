package com.example.AlomShoppingmall.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class OrderRequest {
    @NotNull
    @Size(max = 255)
    private String status;

    @NotNull
    private Integer quantity;

    @NotNull
    private Long userId;

    @NotNull
    private Long productId;

    public @NotNull @Size(max = 255) String getStatus() {
        return status;
    }

    public void setStatus(@NotNull @Size(max = 255) String status) {
        this.status = status;
    }

    public @NotNull Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@NotNull Integer quantity) {
        this.quantity = quantity;
    }

    public @NotNull Long getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Long userId) {
        this.userId = userId;
    }

    public @NotNull Long getProductId() {
        return productId;
    }

    public void setProductId(@NotNull Long productId) {
        this.productId = productId;
    }
}
