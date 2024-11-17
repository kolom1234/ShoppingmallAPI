package com.example.AlomShoppingmall.service;

import com.example.AlomShoppingmall.dto.ProductCategoryRequest;
import com.example.AlomShoppingmall.dto.ProductRequest;
import com.example.AlomShoppingmall.exception.CategoryNotFoundException;
import com.example.AlomShoppingmall.exception.DuplicateCategoryException;
import com.example.AlomShoppingmall.model.Product;
import com.example.AlomShoppingmall.model.ProductCategory;
import com.example.AlomShoppingmall.repository.ProductRepository;
import com.example.AlomShoppingmall.repository.ProductCategoryRepository;
import com.example.AlomShoppingmall.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository categoryRepository;

    @Transactional
    public Product createProduct(ProductRequest productRequest) {
        ProductCategory category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException("카테고리를 찾을 수 없습니다."));

        Product product = new Product();
        product.setCategory(category);
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());

        return productRepository.save(product);
    }

    public List<Product> getProducts(Long productId) {
        if (productId != null) {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("상품을 찾을 수 없습니다."));
            return Collections.singletonList(product);
        }
        return productRepository.findAll();
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("상품을 찾을 수 없습니다.");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductCategory createCategory(ProductCategoryRequest categoryRequest) {
        // 카테고리 이름 중복 체크
        if (categoryRepository.findByCategoryName(categoryRequest.getCategoryName()).isPresent()) {
            throw new DuplicateCategoryException("이미 존재하는 카테고리입니다.");
        }

        ProductCategory category = new ProductCategory();
        category.setCategoryName(categoryRequest.getCategoryName());
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("카테고리를 찾을 수 없습니다.");
        }
        categoryRepository.deleteById(id);
    }
}
