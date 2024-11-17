package com.example.AlomShoppingmall.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductCategoryRequest {
    @NotNull
    @Size(max = 255)
    private String categoryName;

    public @NotNull @Size(max = 255) String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@NotNull @Size(max = 255) String categoryName) {
        this.categoryName = categoryName;
    }
}

