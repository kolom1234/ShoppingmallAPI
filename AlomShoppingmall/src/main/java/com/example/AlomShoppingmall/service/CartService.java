package com.example.AlomShoppingmall.service;

import com.example.AlomShoppingmall.dto.CartRequest;
import com.example.AlomShoppingmall.exception.CartNotFoundException;
import com.example.AlomShoppingmall.exception.ProductNotFoundException;
import com.example.AlomShoppingmall.exception.UserNotFoundException;
import com.example.AlomShoppingmall.model.Cart;
import com.example.AlomShoppingmall.model.Product;
import com.example.AlomShoppingmall.model.User;
import com.example.AlomShoppingmall.repository.CartRepository;
import com.example.AlomShoppingmall.repository.ProductRepository;
import com.example.AlomShoppingmall.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional  // 쓰기 작업이므로 readOnly = false
    public Cart addToCart(CartRequest cartRequest) {
        User user = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        // 기존 장바구니 항목 확인
        Optional<Cart> existingCart = cartRepository.findByUserIdAndProductId(
                user.getId(), product.getId());

        if (existingCart.isPresent()) {
            // 기존 항목이 있으면 수량 업데이트
            Cart cart = existingCart.get();
            cart.setQuantity(cart.getQuantity() + cartRequest.getQuantity());
            return cartRepository.save(cart);
        }

        // 새 장바구니 항목 생성
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product);
        cart.setQuantity(cartRequest.getQuantity());
        cart.setCreatedAt(LocalDateTime.now());

        return cartRepository.save(cart);
    }

    public List<Cart> getCart(Long userId, Long productId, Long categoryId) {
        // 조건에 맞는 장바구니 항목 조회
        List<Cart> carts = cartRepository.findCarts(userId, productId, categoryId);

        if (carts.isEmpty()) {
            // 결과가 없을 때 빈 리스트 반환 또는 예외 처리
            return Collections.emptyList();
        }

        return carts;
    }

    @Transactional
    public void removeFromCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + id));
        cartRepository.delete(cart);
    }
}