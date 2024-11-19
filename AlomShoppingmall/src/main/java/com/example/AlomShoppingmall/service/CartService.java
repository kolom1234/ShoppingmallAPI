package com.example.AlomShoppingmall.service;

import com.example.AlomShoppingmall.dto.CartRequest;
import com.example.AlomShoppingmall.exception.CartNotFoundException;
import com.example.AlomShoppingmall.exception.ProductNotFoundException;
import com.example.AlomShoppingmall.exception.UnauthorizedException;
import com.example.AlomShoppingmall.exception.UserNotFoundException;
import com.example.AlomShoppingmall.model.Cart;
import com.example.AlomShoppingmall.model.Product;
import com.example.AlomShoppingmall.model.User;
import com.example.AlomShoppingmall.repository.CartRepository;
import com.example.AlomShoppingmall.repository.ProductRepository;
import com.example.AlomShoppingmall.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional  // 쓰기 작업이므로 readOnly = false
    public Cart addToCart(CartRequest cartRequest) {
        User user = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User 를 찾을 수 없습니다"));

        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("Product 를 찾을 수 없습니다"));

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
    public void removeFromCart(List<Long> ids, Long userId) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("삭제할 장바구니 항목의 ID가 제공되지 않았습니다.");
        }

        // 사용자가 존재하는지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 해당 ID들의 장바구니 항목을 조회
        List<Cart> carts = cartRepository.findAllById(ids);

        // 존재하지 않는 ID 추출
        List<Long> foundIds = carts.stream()
                .map(Cart::getId)
                .toList();
        List<Long> notFoundIds = ids.stream()
                .filter(id -> !foundIds.contains(id))
                .toList();

        if (!notFoundIds.isEmpty()) {
            throw new CartNotFoundException("다음 장바구니 항목을 찾을 수 없습니다: " + notFoundIds);
        }

        // 사용자의 장바구니 항목인지 검증
        for (Cart cart : carts) {
            if (!cart.getUser().getId().equals(userId)) {
                throw new UnauthorizedException("장바구니 항목에 대한 삭제 권한이 없습니다. Cart ID: " + cart.getId());
            }
        }

        // 장바구니 항목 삭제
        cartRepository.deleteAll(carts);
    }
}