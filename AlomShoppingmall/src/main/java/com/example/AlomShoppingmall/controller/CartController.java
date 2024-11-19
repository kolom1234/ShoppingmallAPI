package com.example.AlomShoppingmall.controller;

import com.example.AlomShoppingmall.dto.CartRequest;
import com.example.AlomShoppingmall.dto.CartResponse;
import com.example.AlomShoppingmall.model.Cart;
import com.example.AlomShoppingmall.model.User;
import com.example.AlomShoppingmall.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

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

    @DeleteMapping
    public ResponseEntity<Void> removeFromCart(
            @RequestParam List<Long> ids,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        cartService.removeFromCart(ids, user.getId());
        return ResponseEntity.noContent().build();
    }

}

