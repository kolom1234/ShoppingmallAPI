package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.ProductCategory;

public class ProductCategoryResponse {
    private Long id;
    private String categoryName;

    // 생성자와 getter
    public ProductCategoryResponse(ProductCategory category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }
    // 생략된 getter

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
