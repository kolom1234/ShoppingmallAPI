package com.example.AlomShoppingmall.model;


import com.example.AlomShoppingmall.exception.InsufficientStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Product {

    public void decreaseStock(int quantity) {
        if (this.stock < quantity) {
            throw new InsufficientStockException("Not enough stock");
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        this.stock += quantity;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    private ProductCategory category;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;
}
