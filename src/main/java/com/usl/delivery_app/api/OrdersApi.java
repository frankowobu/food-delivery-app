package com.usl.delivery_app.api;

import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.OrderData.OrderStatus;
import com.usl.delivery_app.dto.OrdersDto.OrderRequestDto;
import com.usl.delivery_app.service.OrderService.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersApi {

    private OrderServiceImpl orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        try {
            Order newOrder = orderService.createOrder(
                    orderRequestDto.getOrderEmail(),
                    orderRequestDto.getOrderAddress(),
                    orderRequestDto.getOrderPhone(),
                    orderRequestDto.getMealNames()
            );
            return ResponseEntity.ok(newOrder);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while creating order", e);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        if (order == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order with id " + orderId + " not found");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        List<Order> orders = orderService.getOrdersByUser(userId);
        if (orders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No orders found for user with id " + userId);
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        if (orders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No orders found");
        }
        return ResponseEntity.ok(orders);
    }
    @PostMapping("/approve/{orderId}")
    public ResponseEntity<Order> approveOrder(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        if (order == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order with id " + orderId + " not found");
        }
        order.setOrderStatus(OrderStatus.DELIVERED);
        return ResponseEntity.ok(orderService.saveOrder(order));
    }
}