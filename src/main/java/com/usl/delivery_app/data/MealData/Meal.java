package com.usl.delivery_app.data.MealData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "meals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_name")
    private String mealName;

    @Column(name = "meal_price")
    private double mealPrice;

    @Column(name = "meal_type")
    private String mealType;

    @Column(name = "meal_description")
    private String mealDescription;

    @Column(name = "meal_image")
    private String mealImage;


    public Meal(Meal meal) {
        this.id = meal.getId();
        this.mealName = meal.getMealName();
        this.mealPrice = meal.getMealPrice();
        this.mealType = meal.getMealType();
        this.mealDescription = meal.getMealDescription();
        this.mealImage = meal.getMealImage();
    }
}
