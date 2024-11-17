package com.example.AlomShoppingmall.controller;

import com.example.AlomShoppingmall.dto.CartRequest;
import com.example.AlomShoppingmall.dto.CartResponse;
import com.example.AlomShoppingmall.model.Cart;
import com.example.AlomShoppingmall.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> addToCart(@Valid @RequestBody CartRequest cartRequest) {
        Cart cart = cartService.addToCart(cartRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CartResponse(cart));
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart(@RequestParam(required = false) Long userId,
                                                      @RequestParam(required = false) Long productId,
                                                      @RequestParam(required = false) Long productCategoryId) {
        List<Cart> carts = cartService.getCart(userId, productId, productCategoryId);
        List<CartResponse> cartResponses = carts.stream()
                .map(CartResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartResponses);
    }

//    @DeleteMapping
//    public ResponseEntity<Void> removeFromCart(@RequestParam Long id) {
//        cartService.removeFromCart(id);
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping
    public ResponseEntity<Void> removeFromCart(
            @RequestParam(required = true) List<Long> ids,
            @RequestParam(required = true) Long userId) {  // 현재 사용자의 ID를 추가로 받음.
        cartService.removeFromCart(ids, userId);
        return ResponseEntity.noContent().build();
    }

}

