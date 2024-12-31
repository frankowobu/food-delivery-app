package com.usl.delivery_app.dto.OrdersDto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private String orderEmail;
    private String orderAddress;
    private String orderPhone;
    private List<String> mealNames;

    public OrderRequestDto(String orderEmail,  String orderAddress, String orderPhone, List<String> mealNames) {
        this.orderEmail = orderEmail ;
        this.orderAddress = orderAddress;
        this.orderPhone = orderPhone;
        this.mealNames =  mealNames;
    }
}