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

}