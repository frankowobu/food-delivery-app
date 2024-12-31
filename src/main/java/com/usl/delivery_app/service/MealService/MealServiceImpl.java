package com.usl.delivery_app.service.MealService;

import com.usl.delivery_app.data.MealData.Meal;
import com.usl.delivery_app.repository.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MealServiceImpl  implements MealService{

    private MealRepository mealRepository;

    @Override
    public Meal getMealById(Long id) {
        Meal meal = mealRepository.findById(id).orElse(null);
        return meal;
    }

    @Override
    public Meal createMeal(Meal meal) {
        Meal newMeal = mealRepository.save(meal);
        return newMeal;
    }

    @Override
    public Meal updateMeal(Meal meal) {
        Meal updatedMeal = mealRepository.save(meal);
        return updatedMeal;
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);

    }

    @Override
    public List<Meal> getAllMeals() {
        List<Meal> meal = mealRepository.findAll();
        return meal;
    }

    @Override
    public List<Meal> getAllSortedMeals(String sortBy, String order, String filterByType) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && order != null) {
            sort = "desc".equals(order) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        }

        List<Meal> meals;
        if (filterByType != null) {
            meals = mealRepository.findByMealType(filterByType, sort);
        } else {
            meals = mealRepository.findAll(sort);
        }

        return meals;
    }
}
