package com.example.AlomShoppingmall.repository;

import com.example.AlomShoppingmall.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c " +
            "JOIN FETCH c.user " +
            "JOIN FETCH c.product p " +
            "LEFT JOIN FETCH p.category " +
            "WHERE (:userId is null OR c.user.id = :userId) AND " +
            "(:productId is null OR c.product.id = :productId) AND " +
            "(:categoryId is null OR c.product.category.id = :categoryId)")
    List<Cart> findCarts(
            @Param("userId") Long userId,
            @Param("productId") Long productId,
            @Param("categoryId") Long categoryId
    );

    // 기존 장바구니 항목 확인을 위한 메서드
    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
}

