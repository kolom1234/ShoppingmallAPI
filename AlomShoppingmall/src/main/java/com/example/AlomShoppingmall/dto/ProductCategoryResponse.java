package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.ProductCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductCategoryResponse {
    private Long id;
    private String categoryName;

    public ProductCategoryResponse(ProductCategory category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
    }

}
