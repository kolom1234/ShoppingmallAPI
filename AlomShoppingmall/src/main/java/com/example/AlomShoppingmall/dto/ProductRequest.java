package com.example.AlomShoppingmall.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class ProductRequest {
    @Setter
    @Getter
    private String category;

    @NotNull
    @Size(max = 255)
    private String productName;

    @Setter
    @Getter
    private String description;

    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;

    @NotNull
    private Long categoryId;

    public @NotNull Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull Long categoryId) {
        this.categoryId = categoryId;
    }

   // private String categoryName;

    // Getters and setters

    public @NotNull @Size(max = 255) String getProductName() {
        return productName;
    }

    public void setProductName(@NotNull @Size(max = 255) String productName) {
        this.productName = productName;
    }

    public @NotNull Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull Integer stock) {
        this.stock = stock;
    }

    public @NotNull Integer getPrice() {
        return price;
    }

    public void setPrice(@NotNull Integer price) {
        this.price = price;
    }
}
