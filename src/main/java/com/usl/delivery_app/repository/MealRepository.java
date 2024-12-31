package com.usl.delivery_app.repository;

import com.usl.delivery_app.data.MealData.Meal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository  extends JpaRepository<Meal, Long> {
    Meal findByMealName(String mealName);
    List<Meal> findByMealType(String mealType, Sort sort);
}
