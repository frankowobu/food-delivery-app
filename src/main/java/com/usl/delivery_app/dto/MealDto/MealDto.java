package com.usl.delivery_app.dto.MealDto;

import com.usl.delivery_app.data.MealData.Meal;
import lombok.Data;

@Data
public class MealDto {
    private Long id;
    private String mealName;
    private double mealPrice;
    private String mealType;
    private String mealDescription;
    private String mealImage;

    public MealDto(Meal meal) {
        this.id = meal.getId();
        this.mealName = meal.getMealName();
        this.mealPrice = meal.getMealPrice();
        this.mealType = meal.getMealType();
        this.mealDescription = meal.getMealDescription();
        this.mealImage = meal.getMealImage();
    }
}