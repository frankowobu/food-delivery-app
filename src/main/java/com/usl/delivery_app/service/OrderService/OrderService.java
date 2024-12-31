package com.usl.delivery_app.service.OrderService;

import com.usl.delivery_app.data.OrderData.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(String userEmail, String orderAddress, String orderPhone, List<String> mealNames);
    Order getOrder(Long orderId);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
}
