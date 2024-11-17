package com.example.AlomShoppingmall.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequest {
    private String category;

    @NotNull
    @Size(max = 255)
    private String productName;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
