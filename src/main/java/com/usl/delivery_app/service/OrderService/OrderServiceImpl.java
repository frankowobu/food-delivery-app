package com.usl.delivery_app.service.OrderService;

import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.OrderData.OrderStatus;
import com.usl.delivery_app.data.Usersdata.Users;
import com.usl.delivery_app.repository.MealRepository;
import com.usl.delivery_app.repository.OrderRepository;
import com.usl.delivery_app.repository.UsersRepository;
import com.usl.delivery_app.service.OrderService.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private UsersRepository usersRepository;
    private MealRepository mealRepository;


    @Override
    public Order createOrder(String userEmail, String orderAddress, String orderPhone, List<String> mealNames) {
        if (userEmail == null || userEmail.isEmpty()) {
            throw new IllegalArgumentException("User email cannot be null or empty");
        }
        if (mealNames == null || mealNames.isEmpty()) {
            throw new IllegalArgumentException("Meal names cannot be null or empty");
        }

        Users user = usersRepository.findByEmail(userEmail);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + userEmail);
        }

        Order order = new Order();
        order.setUser(user);
        order.setOrderTotal(
                mealNames.stream().mapToDouble(mealName -> {
                    Meal meal = mealRepository.findByMealName(mealName);
                    if (meal == null) {
                        throw new IllegalArgumentException("Meal not found with name: " + mealName);
                    }
                    return meal.getMealPrice();
                }).sum()
        );
        order.setOrderAddress(orderAddress);
        order.setOrderPhone(orderPhone);
        order.setTrackingNumber("TRK" + new Date().getTime());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setMeals(new ArrayList<>());
        mealNames.forEach(mealName -> {
            Meal meal = mealRepository.findByMealName(mealName);
            if (meal == null) {
                throw new IllegalArgumentException("Meal not found with name: " + mealName);
            }
            order.getMeals().add(meal);
        });
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getOrders();
    }

    @Override
    public List<Order> getAllOrders() {
       List<Order>  order =  orderRepository.findAll();
       return order;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
