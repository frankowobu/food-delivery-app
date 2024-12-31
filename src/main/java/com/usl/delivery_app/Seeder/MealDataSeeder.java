//package com.usl.delivery_app.Seeder;
//
//
//import com.github.javafaker.Faker;
//import com.usl.delivery_app.data.MealData.Meal;
//import com.usl.delivery_app.repository.MealRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MealDataSeeder implements CommandLineRunner {
//
//    @Autowired
//    private MealRepository mealRepository;
//
//    private final Faker faker = new Faker();
//
//    @Override
//    public void run(String... args) throws Exception {
//        for (int i = 0; i < 37; i++) {
//            Meal meal = new Meal();
//            meal.setMealName(faker.food().dish());
//            meal.setMealPrice(faker.number().randomDouble(2, 5, 100));
//            meal.setMealDescription(faker.lorem().sentence());
//            meal.setMealImage("https://source.unsplash.com/1600x900/?food" + i);
//            meal.setMealType(faker.food().ingredient());
//
//            mealRepository.save(meal);
//        }
//    }
//}
