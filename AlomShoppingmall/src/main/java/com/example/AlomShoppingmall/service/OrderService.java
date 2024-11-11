package com.example.AlomShoppingmall.service;


import com.example.AlomShoppingmall.dto.OrderRequest;
import com.example.AlomShoppingmall.exception.InsufficientStockException;
import com.example.AlomShoppingmall.exception.OrderNotFoundException;
import com.example.AlomShoppingmall.exception.ProductNotFoundException;
import com.example.AlomShoppingmall.exception.UserNotFoundException;
import com.example.AlomShoppingmall.model.Order;
import com.example.AlomShoppingmall.model.Product;
import com.example.AlomShoppingmall.model.User;
import com.example.AlomShoppingmall.repository.OrderRepository;
import com.example.AlomShoppingmall.repository.ProductRepository;
import com.example.AlomShoppingmall.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order createOrder(OrderRequest orderRequest) {
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다"));

        // 비관적 락으로 재고 처리
        Product product = productRepository.findByIdWithLock(orderRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException("상품을 찾을 수 없습니다"));

        // 재고 확인
        if (product.getStock() < orderRequest.getQuantity()) {
            throw new InsufficientStockException("재고 부족. Available: " + product.getStock());
        }

        // 재고 감소
        product.decreaseStock(orderRequest.getQuantity());
        productRepository.save(product);

        Order order = new Order();
        order.setOrderAt(LocalDateTime.now());
        order.setStatus(orderRequest.getStatus());
        order.setQuantity(orderRequest.getQuantity());
        order.setUser(user);
        order.setProduct(product);

        return orderRepository.save(order);
    }

    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("주문 내역이 없습니다"));

        if ("COMPLETED".equals(order.getStatus())) {
            throw new IllegalStateException("취소 불가합니다");
        }

        // 락으로 재고 처리
        Product product = productRepository.findByIdWithLock(order.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("상품이 없습니다"));

        product.increaseStock(order.getQuantity());
        productRepository.save(product);

        orderRepository.deleteById(id);
    }

    public List<Order> getOrders(Long orderId, LocalDateTime orderDate, Long userId, Long productId) {
        return orderRepository.findOrders(orderId, orderDate, userId, productId);
    }
}

