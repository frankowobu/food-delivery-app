package com.usl.delivery_app.dto.OrdersDto;

import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.data.OrderData.Order;
import com.usl.delivery_app.data.OrderData.OrderStatus;
import com.usl.delivery_app.dto.MealDto.MealDto;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private double orderTotal;
    private String orderAddress;
    private String orderPhone;
    private Date createdOn;
    private String trackingNumber;
    private List<MealDto> meals;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.orderStatus = order.getOrderStatus();
        this.orderTotal = order.getOrderTotal();
        this.orderAddress = order.getOrderAddress();
        this.orderPhone = order.getOrderPhone();
        this.createdOn = order.getCreatedOn();
        this.trackingNumber = order.getTrackingNumber();
        this.meals = order.getMeals().stream().map(MealDto::new).collect(Collectors.toList());
    }
}
