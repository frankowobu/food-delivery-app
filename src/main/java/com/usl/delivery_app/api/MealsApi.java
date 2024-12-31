package com.usl.delivery_app.api;

import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.service.MealService.MealServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/meals")
@AllArgsConstructor
public class MealsApi {
    private MealServiceImpl mealsService;

    @PostMapping("/create")
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        Meal newMeal = mealsService.createMeal(meal);
        return ResponseEntity.ok(newMeal);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Meal> updateMeal(@PathVariable Long id, @RequestBody Meal meal) {
        Meal existingMeal = mealsService.getMealById(id);
        if (existingMeal == null) {
            return ResponseEntity.notFound().build();
        }
        //map the meal tp the existing meal
        existingMeal.setMealName(meal.getMealName());
        existingMeal.setMealPrice(meal.getMealPrice());
        existingMeal.setMealDescription(meal.getMealDescription());
        existingMeal.setMealImage(meal.getMealImage());
        Meal updatedMeal = mealsService.updateMeal(existingMeal);
        return ResponseEntity.ok(updatedMeal);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealsService.deleteMeal(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getMeal(@PathVariable Long id) {
        Meal meal = mealsService.getMealById(id);
        return ResponseEntity.ok(meal);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Meal>> getAllMeals() {
        List<Meal> meal = mealsService.getAllMeals();
        return ResponseEntity.ok(meal);
    }

    @GetMapping("/all/sort")
    public ResponseEntity<List<Meal>> getAllSortedMeals(
            @RequestParam(required = false, defaultValue = "mealName") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String filterByType
    ) {
        List<Meal> meals = mealsService.getAllSortedMeals(sortBy, order, filterByType);
        return ResponseEntity.ok(meals);
    }

}
