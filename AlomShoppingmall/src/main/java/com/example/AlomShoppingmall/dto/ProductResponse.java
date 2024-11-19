package com.example.AlomShoppingmall.dto;

import com.example.AlomShoppingmall.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
    private Integer price;
    private Integer stock;
    private String categoryName;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.categoryName = product.getCategory().getCategoryName();
    }
}

