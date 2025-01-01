package com.usl.delivery_app.dto.MealDto;

import lombok.Data;

@Data
public class CreateMealDto {
    private String mealName;
    private String mealDescription;
    private String mealType;
    private String mealPrice;
    private String mealImage;



}
