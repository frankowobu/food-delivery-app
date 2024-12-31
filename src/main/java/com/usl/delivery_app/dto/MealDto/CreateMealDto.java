package com.usl.delivery_app.dto.MealDto;

import lombok.Data;

@Data
public class CreateMealDto {
    private String mealName;
    private String mealDescription;
    private String mealType;
    private String mealPrice;
    private String mealImage;


    public CreateMealDto(String mealName, String mealDescription, String mealType, String mealPrice, String mealImage) {
        this.mealName = mealName;
        this.mealDescription = mealDescription;
        this.mealType = mealType;
        this.mealPrice = mealPrice;
        this.mealImage = mealImage;
    }
}
