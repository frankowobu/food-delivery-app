package com.usl.delivery_app.service.MealService;

import com.usl.delivery_app.data.MealData.Meal;

import java.util.List;

public interface MealService {
    Meal getMealById(Long id);;
    Meal createMeal(Meal meal);
    Meal updateMeal(Meal meal);
    void deleteMeal(Long id);
    List<Meal> getAllMeals();
    List<Meal> getAllSortedMeals(String sortBy, String order, String filterByType);

}
