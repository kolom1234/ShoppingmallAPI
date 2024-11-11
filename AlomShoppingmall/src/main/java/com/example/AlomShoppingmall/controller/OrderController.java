package com.example.AlomShoppingmall.controller;

import com.example.AlomShoppingmall.dto.OrderRequest;
import com.example.AlomShoppingmall.dto.OrderResponse;
import com.example.AlomShoppingmall.model.Order;
import com.example.AlomShoppingmall.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new OrderResponse(order));
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelOrder(@RequestParam Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(@RequestParam(required = false) Long orderId,
                                                         @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime orderDate,
                                                         @RequestParam(required = false) Long userId,
                                                         @RequestParam(required = false) Long productId) {
        List<Order> orders = orderService.getOrders(orderId, orderDate, userId, productId);
        List<OrderResponse> orderResponses = orders.stream()
                .map(OrderResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderResponses);
    }
}

