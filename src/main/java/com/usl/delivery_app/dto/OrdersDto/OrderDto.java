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


}
