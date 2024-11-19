package com.example.AlomShoppingmall.controller;

import com.example.AlomShoppingmall.dto.ProductCategoryRequest;
import com.example.AlomShoppingmall.dto.ProductCategoryResponse;
import com.example.AlomShoppingmall.dto.ProductRequest;
import com.example.AlomShoppingmall.dto.ProductResponse;
import com.example.AlomShoppingmall.model.Product;
import com.example.AlomShoppingmall.model.ProductCategory;
import com.example.AlomShoppingmall.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductResponse(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts(@RequestParam(required = false) Long productId) {
        List<Product> products = productService.getProducts(productId);
        List<ProductResponse> productResponses = products.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productResponses);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/category")
    public ResponseEntity<ProductCategoryResponse> createCategory(@Valid @RequestBody ProductCategoryRequest categoryRequest) {
        ProductCategory category = productService.createCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductCategoryResponse(category));
    }

    @DeleteMapping("/category")
    public ResponseEntity<Void> deleteCategory(@RequestParam Long id) {
        productService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}


